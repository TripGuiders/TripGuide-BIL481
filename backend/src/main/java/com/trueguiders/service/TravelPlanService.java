package com.trueguiders.service;

import com.trueguiders.dto.*;
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
    
    @Autowired
    private TravelPlanRepository travelPlanRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private PlanItemRepository planItemRepository;
    
    /**
     * Kullanıcı için yeni bir seyahat planı oluşturur
     */
    @Transactional
    public TravelPlanResponse createTravelPlan(TravelPlanRequest request, Long userId) {
        // 1. Şehri bul
        City city = cityRepository.findByNameIgnoreCase(request.getCity())
                .orElseThrow(() -> new RuntimeException("Şehir bulunamadı: " + request.getCity()));
        
        // 2. Kullanıcıyı bul (eğer userId null ise demo user kullan)
        User user = new User();
        user.setId(userId != null ? userId : 1L);
        
        // 3. TravelPlan oluştur
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setUser(user);
        travelPlan.setCity(city);
        travelPlan.setDuration(request.getDays());
        travelPlan.setStartDate(LocalDate.now());
        
        if (request.getPreferences() != null) {
            travelPlan.setPreferences(String.join(",", request.getPreferences()));
        }
        
        travelPlan = travelPlanRepository.save(travelPlan);
        
        // 4. Şehirdeki mekanları rating'e göre al
        List<Place> allPlaces = placeRepository.findTopRatedPlacesByCity(city.getId());
        
        // 5. Günlük itineraryyi oluştur
        Map<Integer, List<ActivityDTO>> dailyItinerary = generateDailyItinerary(
            travelPlan, allPlaces, request.getDays()
        );
        
        // 6. Response oluştur
        return new TravelPlanResponse(
            travelPlan.getId(),
            city.getName(),
            request.getDays(),
            dailyItinerary
        );
    }
    
    /**
     * Günlük aktivite planını oluşturur
     */
    private Map<Integer, List<ActivityDTO>> generateDailyItinerary(
            TravelPlan travelPlan, List<Place> places, int days) {
        
        Map<Integer, List<ActivityDTO>> itinerary = new LinkedHashMap<>();
        
        // Her gün için 4-5 aktivite planla
        int activitiesPerDay = Math.min(5, places.size() / days);
        int placeIndex = 0;
        
        for (int day = 1; day <= days; day++) {
            List<ActivityDTO> dailyActivities = new ArrayList<>();
            
            // Sabah 09:00'dan başla
            int startHour = 9;
            int orderIndex = 0;
            
            for (int i = 0; i < activitiesPerDay && placeIndex < places.size(); i++) {
                Place place = places.get(placeIndex++);
                
                // Ziyaret süresi (varsayılan 2 saat)
                int duration = (place.getVisitDuration() != null) ? 
                    place.getVisitDuration() / 60 : 2;
                
                String startTime = String.format("%02d:00", startHour);
                String endTime = String.format("%02d:00", startHour + duration);
                
                // ActivityDTO oluştur
                ActivityDTO activity = new ActivityDTO(
                    place.getId(),
                    place.getName(),
                    place.getDescription(),
                    startTime,
                    endTime,
                    place.getCategory(),
                    place.getRating()
                );
                
                dailyActivities.add(activity);
                
                // PlanItem kaydet
                PlanItem planItem = new PlanItem(
                    travelPlan, place, day, startTime, endTime, orderIndex++
                );
                planItemRepository.save(planItem);
                
                // Sonraki aktivite için saat güncelle (1 saat ara + aktivite süresi)
                startHour += duration + 1;
                
                // Gün sonuna gelindiyse break
                if (startHour >= 20) break;
            }
            
            itinerary.put(day, dailyActivities);
        }
        
        return itinerary;
    }
    
    /**
     * Kullanıcının tüm planlarını getirir
     */
    public List<TravelPlan> getUserPlans(Long userId) {
        return travelPlanRepository.findByUserId(userId);
    }
    
    /**
     * Plan detaylarını getirir
     */
    public TravelPlanResponse getPlanDetails(Long planId) {
        TravelPlan plan = travelPlanRepository.findById(planId)
            .orElseThrow(() -> new RuntimeException("Plan bulunamadı"));
        
        List<PlanItem> items = planItemRepository.findByTravelPlanIdOrderByDayAscOrderIndexAsc(planId);
        
        // Günlere göre grupla
        Map<Integer, List<ActivityDTO>> dailyItinerary = items.stream()
            .collect(Collectors.groupingBy(
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
            ));
        
        return new TravelPlanResponse(
            plan.getId(),
            plan.getCity().getName(),
            plan.getDuration(),
            dailyItinerary
        );
    }
}