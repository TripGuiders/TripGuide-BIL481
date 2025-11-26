package com.trueguiders.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trueguiders.dto.ActivityDTO;
import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.model.City;
import com.trueguiders.model.Place;
import com.trueguiders.model.PlanItem;
import com.trueguiders.model.TravelPlan;
import com.trueguiders.model.User;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.PlanItemRepository;
import com.trueguiders.repository.TravelPlanRepository;
import com.trueguiders.repository.UserRepository;

@Service
public class TravelPlanService {

    @Autowired private TravelPlanRepository travelPlanRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private PlanItemRepository planItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private NotificationService notificationService;

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

        // 5. Mekanları Getir (En yüksek puanlılar)
        List<Place> allPlaces = placeRepository.findTopRatedPlacesByCity(city.getId());
        if (allPlaces.isEmpty()) {
            allPlaces = placeRepository.findByCityId(city.getId());
        }

        // 6. Günlük Planı Oluştur
        Map<Integer, List<ActivityDTO>> itinerary = generateDailyItinerary(travelPlan, allPlaces, days);

        return new TravelPlanResponse(
                travelPlan.getId(),
                city.getName(),
                days,
                itinerary
        );
    }

    private Map<Integer, List<ActivityDTO>> generateDailyItinerary(TravelPlan travelPlan, List<Place> places, int days) {

        Map<Integer, List<ActivityDTO>> itinerary = new LinkedHashMap<>();
    
        // 1. LİSTEYİ KARIŞTIR (Her planda farklı sıra gelsin)
        Collections.shuffle(places); 

        // 2. İTERATOR KULLAN (Kullanılanı bir daha seçmemek için)
        Iterator<Place> placeIterator = places.iterator();

        // Günde en fazla 3 aktivite
        int activitiesPerDay = 3; 

        for (int day = 1; day <= days; day++) {
            List<ActivityDTO> dailyActivities = new ArrayList<>();
            int hour = 9; // Sabah 09:00

            for (int i = 0; i < activitiesPerDay; i++) {
            
                // KRİTİK KONTROL: Eğer mekan kalmadıysa dur! (Başa dönmek yok)
                if (!placeIterator.hasNext()) {
                    break; 
                }

                Place place = placeIterator.next();
            
                // Süre hesapla (yoksa 2 saat ver)
                int duration = (place.getVisitDuration() != null) ? (place.getVisitDuration() / 60) : 2;
                if (duration < 1) duration = 1;

                String start = String.format("%02d:00", hour);
                String end = String.format("%02d:00", hour + duration);

                // DTO oluştur
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

                // DB Kaydı
                planItemRepository.save(new PlanItem(
                    travelPlan,
                    place,
                    day,
                    start,
                    end,
                    i + 1
                ));

                hour += duration + 1; // 1 saat ara
                if (hour >= 21) break; // Akşam olduysa günü bitir
            }
        
            // Eğer o gün aktivite varsa listeye ekle
            if (!dailyActivities.isEmpty()) {
                itinerary.put(day, dailyActivities);
            }
        }
        return itinerary;
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