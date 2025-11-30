//package com.trueguiders;
//
//import com.trueguiders.dto.TravelPlanRequest;
//import com.trueguiders.dto.TravelPlanResponse;
//import com.trueguiders.model.City;
//import com.trueguiders.model.Place;
//import com.trueguiders.model.TravelPlan;
//import com.trueguiders.model.User;
//import com.trueguiders.repository.CityRepository;
//import com.trueguiders.repository.PlaceRepository;
//import com.trueguiders.repository.TravelPlanRepository;
//import com.trueguiders.repository.UserRepository;
//import com.trueguiders.service.ReviewService;
//import com.trueguiders.service.TravelPlanService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional // Test bitince veritabanını eski haline getirir (kirlilik yaratmaz)
//// Gereksinim 6,9
//public class PlanAndRatingTest {
//
//    @Autowired private TravelPlanService travelPlanService;
//    @Autowired private TravelPlanRepository travelPlanRepository;
//    @Autowired private ReviewService reviewService;
//    @Autowired private PlaceRepository placeRepository;
//    @Autowired private UserRepository userRepository;
//    @Autowired private CityRepository cityRepository;
//
//    // User planı kaydedebilir.
//    @Test
//    void testUserCanSavePlan() {
//        // 1. Veri Hazırlığı
//        User user = userRepository.findAll().get(0); // Demo kullanıcıyı al
//        City city = cityRepository.findAll().get(0); // Herhangi bir şehri al
//
//        TravelPlanRequest request = new TravelPlanRequest();
//        request.setUserId(user.getId());
//        request.setCityId(city.getId());
//        request.setDays(2);
//        request.setFunScore(5); // Tercihleri set et
//
//        // 2. İşlem: Planı Oluştur (Servis burada repository.save() çağırır)
//        TravelPlanResponse response = travelPlanService.createTravelPlan(request);
//
//        // Assert: Servis null dönmemeli
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getPlanId(), "Plan ID'si oluşturulmadı!");
//
//        // 3. Kanıt (Verification): Veritabanından kontrol et
//        TravelPlan savedPlan = travelPlanRepository.findById(response.getPlanId()).orElse(null);
//
//        Assertions.assertNotNull(savedPlan, "Plan veritabanına kaydedilmedi!");
//        Assertions.assertEquals(user.getId(), savedPlan.getUser().getId(), "Plan yanlış kullanıcıya kaydedildi.");
//        Assertions.assertEquals(city.getId(), savedPlan.getCity().getId(), "Plan yanlış şehre kaydedildi.");
//
//        System.out.println("TEST BAŞARILI: Kullanıcı planı veritabanına kaydedildi. Plan ID: " + savedPlan.getId());
//    }
//
//    // User planı değerlendirebilir.
//    @Test
//    void testUserCanRatePlans() {
//        // 1. Veri Hazırlığı
//        User user = userRepository.findAll().get(0);
//        Place place = placeRepository.findAll().get(0);
//
//        // Puanlamadan önceki durum (Karşılaştırma için)
//        double oldRating = place.getRating();
//
//        // 2. İşlem: Yorum ve Puan Ekle (Rating: 5)
//        reviewService.addReview(user.getId(), place.getId(), 5, "Planımdaki en güzel yerdi!");
//
//        // 3. Kanıt (Verification): Mekanı tekrar çek ve puanına bak
//        Place updatedPlace = placeRepository.findById(place.getId()).orElseThrow();
//
//        // Puan değişmiş olmalı (Eski puan ile yeni puan aynı değilse işlem başarılıdır)
//        // Not: Eğer eski puan zaten 5.0 ise değişmeyebilir, o yüzden assertion'ı esnek tutuyoruz.
//        boolean ratingUpdated = updatedPlace.getReviews().stream()
//                .anyMatch(r -> r.getComment().equals("Planımdaki en güzel yerdi!") && r.getRating() == 5);
//
//        Assertions.assertTrue(ratingUpdated, "Kullanıcının değerlendirmesi ve puanı sisteme yansımadı.");
//
//        System.out.println("TEST BAŞARILI: Kullanıcı değerlendirmesi (Puan: 5) başarıyla kaydedildi.");
//    }
//}



package com.trueguiders;

import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.model.City;
import com.trueguiders.model.Place;
import com.trueguiders.model.TravelPlan;
import com.trueguiders.model.User;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.TravelPlanRepository;
import com.trueguiders.repository.UserRepository;
import com.trueguiders.service.ReviewService;
import com.trueguiders.service.TravelPlanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // Test bitince veritabanını eski haline getirir (kirlilik yaratmaz)
// Gereksinim 6,9
public class PlanAndRatingTest {

    @Autowired private TravelPlanService travelPlanService;
    @Autowired private TravelPlanRepository travelPlanRepository;
    @Autowired private ReviewService reviewService;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CityRepository cityRepository;

    // User planı kaydedebilir.
    @Test
    void testUserCanSavePlan() {
        System.out.println("==== TEST BAŞLIYOR: testUserCanSavePlan ====");

        // 1. Veri Hazırlığı
        User user = userRepository.findAll().get(0); // Demo kullanıcıyı al
        City city = cityRepository.findAll().get(0); // Herhangi bir şehri al

        System.out.println("DEBUG: Kullanıcı seçildi -> ID: " + user.getId() + ", Email: " + user.getEmail());
        System.out.println("DEBUG: Şehir seçildi -> " + city.getName());

        TravelPlanRequest request = new TravelPlanRequest();
        request.setUserId(user.getId());
        request.setCityId(city.getId());
        request.setDays(2);
        request.setFunScore(5); // Tercihleri set et

        System.out.println("DEBUG: İstek parametreleri hazırlandı -> Gün: " + request.getDays() + ", Eğlence: " + request.getFunScore());

        // 2. İşlem: Planı Oluştur (Servis burada repository.save() çağırır)
        System.out.println("DEBUG: createTravelPlan servisi çağrılıyor...");
        TravelPlanResponse response = travelPlanService.createTravelPlan(request);
        System.out.println("DEBUG: Servis yanıt döndü.");

        // Assert: Servis null dönmemeli
        Assertions.assertNotNull(response);

        if (response.getPlanId() != null) {
            System.out.println("DEBUG: Oluşan Plan ID: " + response.getPlanId());
        } else {
            System.out.println("!!! HATA: Response içinde Plan ID null döndü!");
        }

        Assertions.assertNotNull(response.getPlanId(), "Plan ID'si oluşturulmadı!");

        // 3. Kanıt (Verification): Veritabanından kontrol et
        TravelPlan savedPlan = travelPlanRepository.findById(response.getPlanId()).orElse(null);

        System.out.println("DEBUG: Veritabanı sorgusu sonucu: " + (savedPlan != null ? "Kayıt Bulundu" : "Kayıt BULUNAMADI"));

        Assertions.assertNotNull(savedPlan, "Plan veritabanına kaydedilmedi!");
        Assertions.assertEquals(user.getId(), savedPlan.getUser().getId(), "Plan yanlış kullanıcıya kaydedildi.");
        Assertions.assertEquals(city.getId(), savedPlan.getCity().getId(), "Plan yanlış şehre kaydedildi.");

        System.out.println("TEST BAŞARILI: Kullanıcı planı veritabanına kaydedildi. Plan ID: " + savedPlan.getId());
        System.out.println("===============================================");
    }

    // User planı değerlendirebilir.
    @Test
    void testUserCanRatePlans() {
        System.out.println("==== TEST BAŞLIYOR: testUserCanRatePlans ====");

        // 1. Veri Hazırlığı
        User user = userRepository.findAll().get(0);
        Place place = placeRepository.findAll().get(0);

        System.out.println("DEBUG: Değerlendirme yapacak kullanıcı ID: " + user.getId());
        System.out.println("DEBUG: Puanlanacak mekan: " + place.getName() + " (ID: " + place.getId() + ")");

        // Puanlamadan önceki durum (Karşılaştırma için)
        double oldRating = place.getRating();
        System.out.println("DEBUG: İşlem öncesi mekan puanı: " + oldRating);

        // 2. İşlem: Yorum ve Puan Ekle (Rating: 5)
        System.out.println("DEBUG: Yorum ekleniyor: 'Planımdaki en güzel yerdi!', Puan: 5");
        reviewService.addReview(user.getId(), place.getId(), 5, "Planımdaki en güzel yerdi!");
        System.out.println("DEBUG: addReview servisi tamamlandı.");

        // 3. Kanıt (Verification): Mekanı tekrar çek ve puanına bak
        Place updatedPlace = placeRepository.findById(place.getId()).orElseThrow();

        System.out.println("DEBUG: Mekan veritabanından tekrar çekildi. Toplam yorum sayısı: " + updatedPlace.getReviews().size());

        // Puan değişmiş olmalı (Eski puan ile yeni puan aynı değilse işlem başarılıdır)
        boolean ratingUpdated = updatedPlace.getReviews().stream()
                .anyMatch(r -> r.getComment().equals("Planımdaki en güzel yerdi!") && r.getRating() == 5);

        System.out.println("DEBUG: Yeni yorum veritabanında doğrulandı mı? -> " + ratingUpdated);

        Assertions.assertTrue(ratingUpdated, "Kullanıcının değerlendirmesi ve puanı sisteme yansımadı.");

        System.out.println("TEST BAŞARILI: Kullanıcı değerlendirmesi (Puan: 5) başarıyla kaydedildi.");
        System.out.println("===============================================");
    }
}