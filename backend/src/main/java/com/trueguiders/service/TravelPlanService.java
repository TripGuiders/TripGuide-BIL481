package com.trueguiders.service;

import com.trueguiders.dto.ActivityDTO;
import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.model.*;
import com.trueguiders.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TravelPlanService {

    @Autowired private TravelPlanRepository travelPlanRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private PlanItemRepository planItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private NotificationService notificationService;
        // Kullanıcının puanlarına göre bir mekana ek tercih skoru ver
        private double preferenceScoreForPlace(Place place, TravelPlanRequest request) {
            if (place.getCategory() == null || request == null) return 0.0;
    
            String cat = place.getCategory().toLowerCase();
            double score = 0.0;
    
            // Boş ise 0 kabul et
            int fun      = request.getFunScore()      != null ? request.getFunScore()      : 0;
            int culture  = request.getCultureScore()  != null ? request.getCultureScore()  : 0;
            int nature   = request.getNatureScore()   != null ? request.getNatureScore()   : 0;
            int beach    = request.getBeachScore()    != null ? request.getBeachScore()    : 0;
            int shopping = request.getShoppingScore() != null ? request.getShoppingScore() : 0;
    
            if (cat.contains("eğlence") || cat.contains("eğlence") || cat.contains("gezi")) {
                score += fun;
            }
            if (cat.contains("tarih") || cat.contains("müze") || cat.contains("müze")) {
                score += culture;
            }
            if (cat.contains("doğa") || cat.contains("doğa") || cat.contains("manzara")) {
                score += nature;
            }
            if (cat.contains("plaj") || cat.contains("sahil")) {
                score += beach;
            }
            if (cat.contains("alışveriş") || cat.contains("alişveriş") || cat.contains("alışveris") ) {
                score += shopping;
            }
    
            return score;
        }
    
        // Rating + tercih skorunu birleştiren toplam skor
        private double combinedScore(Place place, TravelPlanRequest request) {
            double rating = place.getRating() != null ? place.getRating() : 3.0; // rating yoksa 3 varsayalım
            double pref   = preferenceScoreForPlace(place, request);
            // Rating'i biraz daha ağırlıklı yapalım
            return rating * 2.0 + pref;
        }
    
    @Transactional
    public TravelPlanResponse createTravelPlan(TravelPlanRequest request) {

        // 1. Kullanıcıyı Bul (Varsa)
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId()).orElse(null);
        }

        // 2. Şehri Bul
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("Şehir bulunamadı ID: " + request.getCityId()));

        // 3. Gün Sayısını Al (En az 1 gün olsun)
        int days = (request.getDays() != null && request.getDays() > 0) ? request.getDays() : 1;

        // 4. TravelPlan Kaydı Oluştur
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setUser(user);
        travelPlan.setCity(city);
        travelPlan.setDuration(days);
        travelPlan.setStartDate(LocalDate.now().plusDays(1)); // Gezi yarın başlıyor varsayalım
        
        if (request.getPreferences() != null && request.getPreferences().length > 0) {
            travelPlan.setPreferences(String.join(",", request.getPreferences()));
        } else {
            travelPlan.setPreferences("General");
        }

        travelPlan = travelPlanRepository.save(travelPlan);

        // Plan oluştuktan hemen sonra bildirim servisini çağırıyoruz
        notificationService.sendPlanCreatedNotification(travelPlan.getUser(), city.getName());

        // 5. Mekanları Getir (önce tercihlere göre filtrelemeyi dene)
        List<Place> allPlaces = placeRepository.findTopRatedPlacesByCity(city.getId());
        if (allPlaces.isEmpty()) {
            allPlaces = placeRepository.findByCityId(city.getId());
        }

        // Kullanıcının tercihleri
        String[] prefs = request.getPreferences();

        if (prefs != null && prefs.length > 0) {
            // Tercihleri tek tek kategori listesine aç
            List<String> desiredCategories = new ArrayList<>();
            for (String pref : prefs) {
                desiredCategories.addAll(mapPreferenceToCategories(pref));
            }

            if (!desiredCategories.isEmpty()) {
                // Hızlı arama için Set'e çevir
                Set<String> categorySet = new HashSet<>(desiredCategories);

                // En yüksek puanlı listeyi, bu kategorilere göre filtrele
                List<Place> filtered = allPlaces.stream()
                        .filter(p -> p.getCategory() != null && categorySet.contains(p.getCategory()))
                        .collect(Collectors.toList());

                // Eğer filtrelenmiş sonuç boş değilse, onu kullan
                if (!filtered.isEmpty()) {
                    allPlaces = filtered;
                }
                // Boşsa: fallback olarak allPlaces (tüm şehir) kalır
            }
        }

        // Son olarak: en yüksek toplam skordan en düşüğe sırala
        allPlaces.sort((p1, p2) ->
        Double.compare(combinedScore(p2, request), combinedScore(p1, request)));


        // 6. Günlük Planı Oluştur
        // 6. Günlük Planı Oluştur (A* ile optimize)
        Map<Integer, List<ActivityDTO>> itinerary = generateDailyItinerary(travelPlan, allPlaces, days, request);

        return new TravelPlanResponse(
                travelPlan.getId(),
                city.getName(),
                days,
                itinerary
        );
    }

        /**
     * A* benzeri arama kullanarak her gün için en yüksek toplam skorlu rota üretir.
     */
        private Map<Integer, List<ActivityDTO>> generateDailyItinerary(
            TravelPlan travelPlan,
            List<Place> places,
            int days,
            TravelPlanRequest request) {

        Map<Integer, List<ActivityDTO>> itinerary = new LinkedHashMap<>();

        // Aynı yeri iki kere önermemek için kalan mekan listesi
        List<Place> remaining = new ArrayList<>(places);

        // Bir gün için toplam süre (dakika) – 10:00–20:00 arası ~ 8 saat kabul ettim
        int minutesPerDay = 8 * 60;

        for (int day = 1; day <= days; day++) {

            if (remaining.isEmpty()) break;

            // A* ile bu gün için en iyi kombinasyonu bul
            List<Place> todaysPlaces = selectBestPlacesForDayAStar(remaining, minutesPerDay, request);
            if (todaysPlaces.isEmpty()) break;

            List<ActivityDTO> dailyActivities = new ArrayList<>();
            int hour = 10; // 10:00'dan başla

            for (int i = 0; i < todaysPlaces.size(); i++) {
                Place place = todaysPlaces.get(i);

                int durationHours = (place.getVisitDuration() != null)
                        ? Math.max(1, place.getVisitDuration() / 60)
                        : 2; // yoksa 2 saat varsay

                String start = String.format("%02d:00", hour);
                String end = String.format("%02d:00", hour + durationHours);

                ActivityDTO dto = new ActivityDTO(
                        place.getId(),
                        place.getName(),
                        place.getDescription(),
                        start,
                        end,
                        place.getCategory(),
                        place.getRating()
                );
                dailyActivities.add(dto);

                // DB kaydı
                planItemRepository.save(new PlanItem(
                        travelPlan,
                        place,
                        day,
                        start,
                        end,
                        i + 1
                ));

                hour += durationHours + 1; // 1 saat ara
                if (hour >= 19) break;
            }

            if (!dailyActivities.isEmpty()) {
                itinerary.put(day, dailyActivities);
            }

            // Bu gün kullanılan mekanları "remaining" listesinden düş
            remaining.removeAll(todaysPlaces);
        }

        return itinerary;
    }
        /**
     * A* benzeri arama: verilen mekan listesinden, günlük dakika limiti içinde
     * toplam combinedScore'u maksimize eden kombinasyonu seçer.
     */
        private List<Place> selectBestPlacesForDayAStar(List<Place> candidates,
            int minutesPerDay,
            TravelPlanRequest request) {

    // Önce her mekan için skor+duration hesaplayalım
    class ScoredPlace {
    Place place;
    double score;
    int duration; // dakika

    ScoredPlace(Place p) {
    this.place = p;
    int dur = p.getVisitDuration() != null ? p.getVisitDuration() : 120;
    this.duration = dur;
    this.score = combinedScore(p, request);
    }
    }

    List<ScoredPlace> scored = candidates.stream()
    .map(ScoredPlace::new)
    // yüksek skordan düşüğe sırala
    .sorted((a, b) -> Double.compare(b.score, a.score))
    .toList();

    // A* state
    class State {
    List<ScoredPlace> chosen;
    int timeUsed;          // dakika
    double scoreSoFar;     // g(n)
    int index;             // sıradaki bakılacak mekan
    double estimatedTotal; // f(n) = g + h

    State(List<ScoredPlace> chosen, int timeUsed, double scoreSoFar, int index, double estimatedTotal) {
    this.chosen = chosen;
    this.timeUsed = timeUsed;
    this.scoreSoFar = scoreSoFar;
    this.index = index;
    this.estimatedTotal = estimatedTotal;
    }
    }

    // En yüksek f(n) en öne gelsin
    PriorityQueue<State> open = new PriorityQueue<>(
    Comparator.comparingDouble((State s) -> -s.estimatedTotal)
    );

    // Heuristic: kalan zamanda, sıradaki mekanlardan sığdırabildiğimiz kadarının skorunu ekle
    java.util.function.BiFunction<Integer, Integer, Double> heuristic =
    (startIdx, remainingMinutes) -> {
    double h = 0.0;
    int timeLeft = remainingMinutes;
    for (int i = startIdx; i < scored.size(); i++) {
    ScoredPlace sp = scored.get(i);
    if (sp.duration <= timeLeft) {
    h += sp.score;
    timeLeft -= sp.duration;
    }
    }
    return h;
    };

    // başlangıç durumu
    open.add(new State(
    new ArrayList<>(),
    0,
    0.0,
    0,
    heuristic.apply(0, minutesPerDay)
    ));

    State best = null;

    while (!open.isEmpty()) {
    State current = open.poll();

    if (best == null || current.scoreSoFar > best.scoreSoFar) {
    best = current;
    }

    if (current.index >= scored.size()) continue;

    ScoredPlace next = scored.get(current.index);

    // 1) Bu mekanı AL
    if (current.timeUsed + next.duration <= minutesPerDay) {
    List<ScoredPlace> newChosen = new ArrayList<>(current.chosen);
    newChosen.add(next);

    int newTime = current.timeUsed + next.duration;
    double newScore = current.scoreSoFar + next.score;

    double est = newScore + heuristic.apply(
    current.index + 1,
    minutesPerDay - newTime
    );

    open.add(new State(newChosen, newTime, newScore, current.index + 1, est));
    }

    // 2) Bu mekanı PAS geç
    double estSkip = current.scoreSoFar + heuristic.apply(
    current.index + 1,
    minutesPerDay - current.timeUsed
    );
    open.add(new State(current.chosen, current.timeUsed, current.scoreSoFar,
    current.index + 1, estSkip));
    }

    if (best == null) return List.of();

    return best.chosen.stream()
        .map(sp -> sp.place)
        .toList();
    }


        // Kullanıcıdan gelen tercih kodunu (frontend'teki value) Place.category'ye çevirir
        private List<String> mapPreferenceToCategories(String pref) {
        if (pref == null) return List.of();

        return switch (pref.toLowerCase()) {
            case "eglence" -> List.of("Eğlence", "Gezi", "Tema Parkı"); // Land of Legends, Balon Turu vs.
            case "tarih"   -> List.of("Tarih", "Müze");
            case "plaj"    -> List.of("Plaj");
            case "doga"    -> List.of("Doğa", "Manzara");
            case "alisveris" -> List.of("Alışveriş");
            default -> List.of(); // tanınmayan tercih
        };
    }

    // Kullanıcının Planlarını Getir
    public List<TravelPlanResponse> getUserPlans(Long userId) {
        List<TravelPlan> plans = travelPlanRepository.findByUserId(userId);
        return plans.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    // Plan Detayı Getir
    public TravelPlanResponse getPlanDetails(Long planId) {
        TravelPlan plan = travelPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan bulunamadı"));
        
        // PlanItem'ları çek ve düzenle...
        // Basitlik için burada direkt entity'den çeviriyoruz:
        return convertToResponse(plan);
    }

    // Yardımcı Metot: Entity -> Response DTO
    private TravelPlanResponse convertToResponse(TravelPlan plan) {
        // PlanItemları çekmemiz lazım (Lazy loading hatası almamak için repository'den çekmek daha güvenli)
        List<PlanItem> items = planItemRepository.findByTravelPlanIdOrderByDayAscOrderIndexAsc(plan.getId());
        
        Map<Integer, List<ActivityDTO>> daily = items.stream().collect(
                Collectors.groupingBy(
                        PlanItem::getDay,
                        LinkedHashMap::new,
                        Collectors.mapping(item -> new ActivityDTO(
                                item.getPlace().getId(),
                                item.getPlace().getName(),
                                item.getPlace().getDescription(),
                                item.getStartTime(),
                                item.getEndTime(),
                                item.getPlace().getCategory(),
                                item.getPlace().getRating()
                        ), Collectors.toList())
                )
        );

        return new TravelPlanResponse(
                plan.getId(),
                plan.getCity().getName(),
                plan.getDuration(),
                daily
        );
    }
}