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

    @Autowired private CityRepository cityRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // 👉 DEV MOD: Her restart’ta demo verilerini sıfırla
        // (önce place'leri sil, yoksa FK hatası verir)
        placeRepository.deleteAll();
        cityRepository.deleteAll();

        if (userRepository.count() == 0) {
            createUser();
        }

        // --------- ŞEHİRLER ---------
        City antalya    = createCity("Antalya",    "Türkiye", "Turizmin başkenti.");
        City istanbul   = createCity("İstanbul",   "Türkiye", "Tarih ve kültür mozaiği.");
        City izmir      = createCity("İzmir",      "Türkiye", "Ege'nin incisi.");
        City kapadokya  = createCity("Kapadokya",  "Türkiye", "Peri bacaları diyarı.");
        City paris      = createCity("Paris",      "Fransa",  "Aşıklar şehri.");

        City artvin     = createCity("Artvin",     "Türkiye", "Yeşilin bin bir tonunu barındıran doğa cenneti.");
        City konya      = createCity("Konya",      "Türkiye", "Mevlana'nın şehri, tarihle dolu Anadolu merkezi.");
        City canakkale  = createCity("Çanakkale",  "Türkiye", "Tarihin en önemli savaşının yaşandığı şehir.");
        City berlin     = createCity("Berlin",     "Almanya", "Tarihi ve modernliği bir arada sunan başkent.");
        City kilis      = createCity("Kilis",      "Türkiye", "Tarihi dokusu ve gastronomisiyle ünlü sınır şehri.");

        City bodrum     = createCity("Bodrum",     "Türkiye", "Ege'nin eğlence ve tatil cenneti.");
        City roma       = createCity("Roma",       "İtalya",  "Antik dünyanın kalbi, tarihin başkenti.");
        City barselona  = createCity("Barselona",  "İspanya", "Gaudi mimarisi ve sahilleriyle ünlü şehir.");
        City londra     = createCity("Londra",     "Birleşik Krallık", "Dünyanın en kozmopolit şehirlerinden biri.");

        // --------- MEKANLAR ---------
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

        // --- BODRUM ---
        createPlace("Bodrum Kalesi", bodrum, "Tarih", "Sualtı Arkeoloji Müzesi'ne ev sahipliği yapan tarihi kale.", 4.7, 120);
        createPlace("Barlar Sokağı", bodrum, "Gezi", "Eğlence ve gece hayatının merkezi.", 4.5, 180);
        createPlace("Bodrum Marina", bodrum, "Gezi", "Restoranlar ve yat limanı ile ünlü.", 4.6, 90);
        createPlace("Türkbükü Sahili", bodrum, "Plaj", "Lüks beach clublarıyla ünlü sahil.", 4.7, 180);
        createPlace("Gümüşlük", bodrum, "Gezi", "Gün batımı ve balık restoranlarıyla ünlü köy.", 4.8, 150);

        // --- ROMA ---
        createPlace("Kolezyum", roma, "Tarih", "Roma'nın en ünlü amfitiyatrosu.", 5.0, 120);
        createPlace("Vatikan Müzeleri", roma, "Müze", "Michelangelo'nun eserlerinin bulunduğu dünya müzesi.", 4.9, 180);
        createPlace("Piazza Navona", roma, "Gezi", "Barok tarzı meydan.", 4.8, 90);
        createPlace("Pantheon", roma, "Tarih", "Antik Roma'nın en iyi korunmuş tapınağı.", 4.9, 60);
        createPlace("Trevi Çeşmesi", roma, "Tarih", "Dilek fıskiyesi olarak bilinen ünlü çeşme.", 4.8, 45);

        // --- BARSELONA ---
        createPlace("Sagrada Familia", barselona, "Tarih", "Gaudi'nin yarım kalmış şaheseri.", 5.0, 120);
        createPlace("Park Güell", barselona, "Manzara", "Gaudi'nin renkli parkı.", 4.9, 120);
        createPlace("La Rambla", barselona, "Gezi", "Barselona'nın en ünlü caddesi.", 4.6, 120);
        createPlace("Barceloneta Plajı", barselona, "Plaj", "Şehrin en popüler plajı.", 4.7, 180);
        createPlace("Casa Batlló", barselona, "Tarih", "Gaudi'nin modernist eseri.", 4.8, 90);

        // --- LONDRA ---
        createPlace("London Eye", londra, "Manzara", "Şehir manzarasını görebileceğiniz dev dönme dolap.", 4.7, 60);
        createPlace("British Museum", londra, "Müze", "Dünyanın en ünlü müzelerinden biri.", 4.9, 180);
        createPlace("Tower Bridge", londra, "Tarih", "Londra'nın ikonik köprüsü.", 4.8, 90);
        createPlace("Buckingham Sarayı", londra, "Tarih", "İngiliz kraliyet ailesinin resmi ikametgahı.", 4.7, 120);
        createPlace("Hyde Park", londra, "Doğa", "Londra'nın en büyük parklarından biri.", 4.6, 120);
        createPlace("Camden Town", londra, "Gezi", "Alternatif kültürün ve sokak pazarlarının merkezi.", 4.5, 120);
        createPlace("Oxford Street", londra, "Alışveriş", "Avrupa'nın en yoğun alışveriş caddesi.", 4.6, 180);
        createPlace("Natural History Museum", londra, "Müze", "Dinozorlar ve doğa tarihi koleksiyonlarıyla ünlü müze.", 4.8, 150);
        createPlace("Notting Hill", londra, "Gezi", "Renkli evleriyle ünlü semt.", 4.7, 120);
        createPlace("St. Paul's Cathedral", londra, "Tarih", "Londra'nın en ikonik katedrallerinden biri.", 4.8, 90);

        System.out.println(">> Demo şehir ve mekan verileri yüklendi.");
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
    }
}
