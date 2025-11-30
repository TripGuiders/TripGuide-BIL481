//package com.trueguiders;
//
//import com.trueguiders.dto.TravelPlanRequest;
//import com.trueguiders.dto.TravelPlanResponse;
//import com.trueguiders.model.City;
//import com.trueguiders.repository.CityRepository;
//import com.trueguiders.service.TravelPlanService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//
//// Gereksinim 1,2,3,4,5,10
//public class AlgorithmLogicTest {
//
//    @Autowired
//    private TravelPlanService travelPlanService;
//    @Autowired
//    private CityRepository cityRepository;
//
//    @Test
//    void testRouteSuggestionLogic() {
//        // İstanbul verisini çek
//        City city = cityRepository.findAll().stream()
//                .filter(c -> c.getName().equals("İstanbul"))
//                .findFirst().orElseThrow();
//
//        // İstek oluştur (Kültür odaklı)
//        TravelPlanRequest request = new TravelPlanRequest();
//        request.setCityId(city.getId());
//        request.setDays(1); // Gezi süresini belirlemeyi test ediyor.
//        request.setCultureScore(5); // Sadece kültür istiyoruz
//        request.setFunScore(1);
//        request.setUserId(1L);
//
//        TravelPlanResponse response = travelPlanService.createTravelPlan(request);
//
//        // Doğrulamalar
//        Assertions.assertNotNull(response);
//        Assertions.assertFalse(response.getItinerary().get(1).isEmpty(), "Rota boş döndü!");
//
//        // Dönen aktivitelerin içinde "Tarih" veya "Müze" var mı?
//        boolean hasCulture = response.getItinerary().get(1).stream()
//                .anyMatch(a -> a.getCategory().contains("Tarih") || a.getCategory().contains("Müze"));
//
//        Assertions.assertTrue(hasCulture, "Kültür puanı 5 olmasına rağmen tarihsel mekan önerilmedi!");
//
//        System.out.println("Rota Algoritma Testi Başarılı");
//    }
//}



package com.trueguiders;

import com.trueguiders.dto.ActivityDTO; // Bunu eklemeyi unutmayın
import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.model.City;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.service.TravelPlanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
// Gereksinim 1,2,3,4,5,10
public class AlgorithmLogicTest {

    @Autowired
    private TravelPlanService travelPlanService;
    @Autowired
    private CityRepository cityRepository;

    @Test
    void testRouteSuggestionLogic() {
        // --- ADIM 1: Veri Hazırlığı ---
        City city = cityRepository.findAll().stream()
                .filter(c -> c.getName().equals("İstanbul"))
                .findFirst().orElseThrow();

        System.out.println("DEBUG: Şehir bulundu: " + city.getName() + " (ID: " + city.getId() + ")");

        // İstek oluştur (Kültür odaklı, 1 Günlük)
        TravelPlanRequest request = new TravelPlanRequest();
        request.setCityId(city.getId());
        request.setDays(1);
        request.setCultureScore(5); // Sadece kültür istiyoruz
        request.setFunScore(1);
        request.setUserId(1L);

        System.out.println("DEBUG: İstek parametreleri -> Gün: " + request.getDays() + ", Kültür: " + request.getCultureScore());

        // --- ADIM 2: Servis Çağrısı ---
        TravelPlanResponse response = travelPlanService.createTravelPlan(request);

        // --- ADIM 3: Temel Doğrulamalar ---
        Assertions.assertNotNull(response);
        System.out.println("DEBUG: Servis yanıtı başarılı.");

        List<ActivityDTO> dailyActivities = response.getItinerary().get(1);
        Assertions.assertFalse(dailyActivities.isEmpty(), "Rota boş döndü!");

        // --- ADIM 4: Kategori Kontrolü (Gereksinim 3,4) ---
        boolean hasCulture = dailyActivities.stream()
                .anyMatch(a -> a.getCategory().contains("Tarih") || a.getCategory().contains("Müze"));

        System.out.println("DEBUG: Kategori Kontrolü -> 'Tarih/Müze' bulundu mu? " + hasCulture);
        Assertions.assertTrue(hasCulture, "Kültür puanı 5 olmasına rağmen tarihsel mekan önerilmedi!");

        // --- ADIM 5: Zaman Çizelgesi ve Sıralama Kontrolü (Gereksinim 5) ---
        System.out.println("\nDEBUG: --- Zaman Çizelgesi Kontrolü Başlıyor ---");

        String previousTime = "00:00"; // Günün başı

        for (ActivityDTO activity : dailyActivities) {
            String currentTime = activity.getStartTime();
            String placeName = activity.getPlaceName();

            // Çıktı: "DEBUG: 09:00 - Ayasofya" gibi
            System.out.println("DEBUG: Zaman: " + currentTime + " -> Mekan: " + placeName);

            // Kontrol: Şimdiki aktivitenin saati, bir önceki aktiviteden küçük (önce) OLMAMALI.
            // String karşılaştırması "09:00".compareTo("11:00") negatif döner (küçüktür).
            // Biz currentTime >= previousTime olmasını bekliyoruz.
            if (currentTime.compareTo(previousTime) < 0) {
                System.out.println("HATA: Zaman çizelgesinde mantıksız atlama tespit edildi!");
                System.out.println("HATA DETAYI: " + currentTime + ", " + previousTime + "'dan önce geliyor.");
            }

            Assertions.assertTrue(currentTime.compareTo(previousTime) >= 0,
                    "Zaman çizelgesi hatası! " + placeName + " (" + currentTime + "), önceki aktiviteden (" + previousTime + ") önce başlıyor.");

            // Bir sonraki döngü için şimdiki zamanı "önceki" yap
            previousTime = currentTime;
        }

        System.out.println("DEBUG: --- Zaman Çizelgesi Tutarlı ---");
        System.out.println("Rota Algoritma Testi Başarılı");
    }
}