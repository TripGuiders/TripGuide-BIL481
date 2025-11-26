package com.trueguiders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.trueguiders.model.City;
import com.trueguiders.model.Place;
import com.trueguiders.model.User;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Eğer City verisi varsa, tekrar yüklemeyi atla.
        // Ancak User verisi yoksa, örnek kullanıcıyı ekle.
        if (cityRepository.count() > 0) {
            if (userRepository.count() == 0) {
                createUser();
            }
            System.out.println("Veriler zaten mevcut, başlatma atlandı (Kullanıcı eklenebilir).");
            return;
        }

        // 1. Kullanıcı Oluştur
        createUser();

        // 2. Şehirleri Oluştur
        City antalya = createCity("Antalya", "Türkiye", "Turizmin başkenti, turkuaz sahilleri ve tarihi kaleleriyle.");
        City istanbul = createCity("İstanbul", "Türkiye", "Asya ve Avrupa'nın buluştuğu, tarih ve modernliğin iç içe geçtiği eşsiz şehir.");
        City izmir = createCity("İzmir", "Türkiye", "Ege'nin incisi, antik tarih ve modern yaşamın mükemmel uyumu.");
        City kapadokya = createCity("Kapadokya", "Türkiye", "Peri bacaları diyarı, eşsiz doğal oluşumlarıyla.");
        City paris = createCity("Paris", "Fransa", "Aşıklar şehri, sanat ve kültürün merkezi.");
        
        // 3. MEKANLARI EKLE (Kapsamlı liste korundu)

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
        createPlace("Termessos", antalya, "Tarih", "Dağ yamacındaki antik kent", 4.4, 150);


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


        System.out.println("Başlangıç verileri (Şehir, Mekan ve Kullanıcı) başarıyla yüklendi!");
    }

    private void createUser() {
        // Not: Şifreleme (PasswordEncoder) burada kullanılmadı, model/repo düzeyinde ele alınması gerekebilir.
        User u = new User("Gezgin", "test@trueguiders.com", "123456");
        userRepository.save(u);
    }

    private City createCity(String name, String country, String desc) {
        City city = new City(name, country, desc);
        return cityRepository.save(city);
    }

    private Place createPlace(String name, City city, String cat, String desc, Double rate, Integer dur) {
        Place p = new Place(name, city, cat, desc, dur);
        p.setRating(rate);
        // İlk versiyonda olan sabit saatler eklenebilir.
        // p.setOpeningTime("09:00"); 
        // p.setClosingTime("18:00");
        return placeRepository.save(p);
    }
}