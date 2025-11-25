<<<<<<< HEAD
// DataInitializer.java - Başlangıç verilerini yükler
=======
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
package com.trueguiders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.trueguiders.model.City;
import com.trueguiders.model.Place;
<<<<<<< HEAD
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
=======
import com.trueguiders.model.User;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.UserRepository;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432

@Component
public class DataInitializer implements CommandLineRunner {
    
<<<<<<< HEAD
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
=======
    @Autowired private CityRepository cityRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Veri varsa çalışma (Ama User yoksa User ekle)
        if (cityRepository.count() > 0) {
            if (userRepository.count() == 0) createUser();
            System.out.println("Veriler zaten var.");
            return;
        }

        // 1. Kullanıcı Oluştur
        createUser();
        
        // 2. Şehirleri Oluştur
        City antalya = createCity("Antalya", "Türkiye", "Turizmin başkenti.");
        City istanbul = createCity("İstanbul", "Türkiye", "Tarih ve kültür mozaiği.");
        City izmir = createCity("İzmir", "Türkiye", "Ege'nin incisi.");
        City kapadokya = createCity("Kapadokya", "Türkiye", "Peri bacaları diyarı."); // Nevşehir yerine Kapadokya yazdık
        City paris = createCity("Paris", "Fransa", "Aşıklar şehri.");

        // 3. MEKANLARI EKLE (Her şehir için en az 9-10 tane)

        // --- ANTALYA ---
        createPlace("Kaleiçi", antalya, "Tarih", "Eski şehir merkezi.", 4.8, 120);
        createPlace("Düden Şelalesi", antalya, "Doğa", "Denize dökülen şelale.", 4.7, 60);
        createPlace("Konyaaltı Plajı", antalya, "Plaj", "Ünlü sahil şeridi.", 4.6, 180);
        createPlace("Antalya Müzesi", antalya, "Müze", "Roma dönemi eserleri.", 4.9, 90);
        createPlace("Aspendos Tiyatrosu", antalya, "Tarih", "En iyi korunan antik tiyatro.", 4.9, 120);
        createPlace("Perge Antik Kenti", antalya, "Tarih", "Stadyum ve sütunlu cadde.", 4.7, 120);
        createPlace("Lara Plajı", antalya, "Plaj", "Kumsal ve lüks oteller.", 4.5, 180);
        createPlace("Kurşunlu Şelalesi", antalya, "Doğa", "Tabiat parkı.", 4.4, 90);
        createPlace("Tahtalı Dağı", antalya, "Doğa", "Teleferik ile zirveye çıkış.", 4.8, 150);
        createPlace("Land of Legends", antalya, "Eğlence", "Tema parkı.", 4.7, 240);

        // --- İSTANBUL ---
        createPlace("Ayasofya", istanbul, "Tarih", "Dünya mimarlık tarihi incisi.", 5.0, 90);
        createPlace("Topkapı Sarayı", istanbul, "Tarih", "Osmanlı sultanlarının sarayı.", 4.9, 150);
        createPlace("Sultanahmet Camii", istanbul, "Tarih", "Mavi çinileriyle ünlü cami.", 4.8, 60);
        createPlace("Kapalıçarşı", istanbul, "Alışveriş", "Tarihi alışveriş merkezi.", 4.6, 120);
        createPlace("Galata Kulesi", istanbul, "Manzara", "Panoramik İstanbul manzarası.", 4.7, 60);
        createPlace("Yerebatan Sarnıcı", istanbul, "Tarih", "Büyüleyici su sarnıcı.", 4.8, 45);
        createPlace("Dolmabahçe Sarayı", istanbul, "Tarih", "Geç Osmanlı dönemi sarayı.", 4.9, 120);
        createPlace("Boğaz Turu", istanbul, "Gezi", "İstanbul Boğazı tekne turu.", 4.8, 120);
        createPlace("Pierre Loti Tepesi", istanbul, "Manzara", "Haliç manzaralı tepede çay keyfi.", 4.5, 90);
        createPlace("İstiklal Caddesi", istanbul, "Gezi", "Taksim'in simgesi.", 4.4, 120);

        // --- İZMİR ---
        createPlace("Saat Kulesi", izmir, "Tarih", "İzmir'in simgesi.", 4.7, 30);
        createPlace("Kemeraltı Çarşısı", izmir, "Alışveriş", "Tarihi çarşı.", 4.6, 120);
        createPlace("Efes Antik Kenti", izmir, "Tarih", "Dünya mirası antik kent.", 5.0, 180);
        createPlace("Kordon Boyu", izmir, "Gezi", "Deniz kenarı yürüyüş yolu.", 4.8, 90);
        createPlace("Asansör", izmir, "Manzara", "Tarihi asansör ve manzara.", 4.7, 60);
        createPlace("Şirince Köyü", izmir, "Gezi", "Tarihi Rum evleri ve şarap.", 4.6, 150);
        createPlace("Çeşme Kalesi", izmir, "Tarih", "Osmanlı kalesi ve müze.", 4.5, 60);
        createPlace("Alaçatı Sokakları", izmir, "Gezi", "Taş evler ve rüzgar sörfü.", 4.7, 120);
        createPlace("Agora Ören Yeri", izmir, "Tarih", "Antik çarşı.", 4.4, 60);

        // --- KAPADOKYA ---
        createPlace("Göreme Açık Hava Müzesi", kapadokya, "Tarih", "Kaya kiliseleri.", 5.0, 120);
        createPlace("Uçhisar Kalesi", kapadokya, "Manzara", "En yüksek peri bacası.", 4.8, 60);
        createPlace("Ihlara Vadisi", kapadokya, "Doğa", "Kanyon yürüyüşü.", 4.9, 180);
        createPlace("Derinkuyu Yeraltı Şehri", kapadokya, "Tarih", "Derin yeraltı şehri.", 4.7, 90);
        createPlace("Balon Turu", kapadokya, "Eğlence", "Gün doğumu balon keyfi.", 5.0, 180);

         // --- PARİS ---
        createPlace("Eyfel Kulesi", paris, "Manzara", "Paris'in simgesi.", 4.9, 120);
        createPlace("Louvre Müzesi", paris, "Müze", "Mona Lisa'nın evi.", 4.9, 240);
        createPlace("Notre Dame", paris, "Tarih", "Gotik katedral.", 4.7, 60);
        createPlace("Şanzelize Caddesi", paris, "Alışveriş", "Ünlü alışveriş caddesi.", 4.6, 120);
        createPlace("Zafer Takı", paris, "Tarih", "Napolyon'un zafer anıtı.", 4.7, 60);
        createPlace("Montmartre", paris, "Gezi", "Ressamlar tepesi.", 4.8, 120);
        createPlace("Sacré-Cœur Bazilikası", paris, "Tarih", "Beyaz kilise.", 4.7, 60);
        createPlace("Sen Nehri Turu", paris, "Gezi", "Nehirde tekne gezisi.", 4.8, 90);
        createPlace("Orsay Müzesi", paris, "Müze", "Empresyonist sanat.", 4.8, 120);
        createPlace("Lüksemburg Bahçesi", paris, "Doğa", "Şehir parkı.", 4.6, 60);


        System.out.println("Tüm şehirler ve mekanlar yüklendi!");
    }
    
    private void createUser() {
        User u = new User("Gezgin", "test@trueguiders.com", "123456");
        userRepository.save(u);
    }
    
    private City createCity(String name, String country, String desc) {
        return cityRepository.save(new City(name, country, desc));
    }
    
    private Place createPlace(String name, City city, String cat, String desc, Double rate, Integer dur) {
        Place p = new Place(name, city, cat, desc, dur);
        p.setRating(rate);
        return placeRepository.save(p);
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    }
}