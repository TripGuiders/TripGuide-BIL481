// CorsConfig.java
package com.trueguiders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}

// DataInitializer.java - Başlangıç verilerini yükler
package com.trueguiders.config;

import com.trueguiders.model.City;
import com.trueguiders.model.Place;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Eğer veri varsa yeniden ekleme
        if (cityRepository.count() > 0) {
            System.out.println("Veriler zaten mevcut, başlatma atlandı.");
            return;
        }
        
        // Şehirleri ekle
        City antalya = createCity("Antalya", "Türkiye", "Turkuaz sahilleri, tarihi kaleleri ve doğal güzellikleriyle Türkiye'nin incisi.");
        City istanbul = createCity("İstanbul", "Türkiye", "Asya ve Avrupa'nın buluştuğu, tarih ve modernliğin iç içe geçtiği eşsiz şehir.");
        City izmir = createCity("İzmir", "Türkiye", "Ege'nin incisi, antik tarih ve modern yaşamın mükemmel uyumu.");
        City konya = createCity("Konya", "Türkiye", "Mevlana'nın şehri, derin tarihi ve kültürel mirası ile.");
        
        // Antalya mekanları
        createPlace("Kaleiçi", antalya, "şehir merkezi", "Tarihi Kaleiçi bölgesinde dar sokaklarda yürüyüş", 4.5, 120);
        createPlace("Antalya Müzesi", antalya, "müze", "Türkiye'nin en önemli müzelerinden biri", 4.7, 90);
        createPlace("Düden Şelalesi", antalya, "turistik", "Doğal güzellik ve şelale", 4.3, 60);
        createPlace("Aspendos Tiyatrosu", antalya, "turistik", "Antik Roma tiyatrosu", 4.8, 90);
        createPlace("Perge Antik Kenti", antalya, "müze", "Roma döneminden kalma antik kent", 4.6, 120);
        createPlace("Termessos", antalya, "turistik", "Dağ yamacındaki antik kent", 4.4, 150);
        createPlace("Lara Sahili", antalya, "eğlence parkı", "Plaj ve su sporları", 4.2, 180);
        
        // İstanbul mekanları
        createPlace("Ayasofya", istanbul, "müze", "Dünya mirası listesindeki tarihi yapı", 4.9, 120);
        createPlace("Topkapı Sarayı", istanbul, "müze", "Osmanlı padişahlarının sarayı", 4.8, 150);
        createPlace("Sultanahmet Camii", istanbul, "turistik", "Tarihi Mavi Cami", 4.7, 60);
        createPlace("Kapalıçarşı", istanbul, "şehir merkezi", "Dünyanın en eski kapalı çarşılarından biri", 4.5, 90);
        createPlace("Galata Kulesi", istanbul, "turistik", "İstanbul'un panoramik manzarası", 4.6, 45);
        createPlace("Boğaz Turu", istanbul, "eğlence parkı", "Boğaz'da tekne turu", 4.7, 120);
        
        System.out.println("Başlangıç verileri başarıyla yüklendi!");
    }
    
    private City createCity(String name, String country, String description) {
        City city = new City(name, country, description);
        return cityRepository.save(city);
    }
    
    private Place createPlace(String name, City city, String category, String description, Double rating, Integer duration) {
        Place place = new Place(name, city, category, description, duration);
        place.setRating(rating);
        place.setOpeningTime("09:00");
        place.setClosingTime("18:00");
        return placeRepository.save(place);
    }
}