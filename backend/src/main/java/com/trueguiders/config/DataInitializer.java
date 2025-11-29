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
        City atina = createCity("Atina", "Yunanistan", "Antik Yunan’ın kalbi ve Akropolis'in evi.");
        City girit = createCity("Girit", "Yunanistan", "Mitolojinin ve doğal güzelliklerin adası, Yunanistan’ın en büyük adası.");

        // --------- MEKANLAR ---------
        // --- ANTALYA ---
        createPlace("Kaleiçi", antalya, "Tarih", "Eski şehir merkezi.", 4.8, 240);
        createPlace("Düden Şelalesi", antalya, "Doğa", "Denize dökülen şelale.", 4.7, 120);
        createPlace("Konyaaltı Plajı", antalya, "Plaj", "Ünlü sahil şeridi.", 4.6, 240);
        createPlace("Antalya Müzesi", antalya, "Müze", "Roma dönemi eserleri.", 4.9, 180);
        createPlace("Aspendos Tiyatrosu", antalya, "Tarih", "En iyi korunan antik tiyatro.", 4.9, 240);
        createPlace("Perge Antik Kenti", antalya, "Tarih", "Stadyum ve sütunlu cadde.", 4.7, 240);
        createPlace("Lara Plajı", antalya, "Plaj", "Kumsal ve lüks oteller.", 4.5, 360);
        createPlace("Kurşunlu Şelalesi", antalya, "Doğa", "Tabiat parkı.", 4.4, 180);
        createPlace("Tahtalı Dağı", antalya, "Doğa", "Teleferik ile zirveye çıkış.", 4.8, 300);
        createPlace("Land of Legends", antalya, "Eğlence", "Tema parkı.", 4.7, 300);

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
        createPlace("Paşabağ Vadisi", kapadokya, "Doğa","Ünlü peri bacalarını yakından görebileceğiniz vadi.", 4.8, 120);
        createPlace("Avanos Çanak Atölyeleri", kapadokya, "Gezi","Kızılırmak kenarında çanak-çömlek yapım atölyeleri.", 4.6, 120);
        createPlace("Zelve Açık Hava Müzesi", kapadokya, "Tarih","Kayalara oyulmuş eski yerleşim alanı.", 4.7, 120);
        createPlace("Aşk Vadisi", kapadokya, "Doğa","Manzarasıyla ünlü yürüyüş rotası.", 4.7, 150);
        createPlace("Ürgüp Şarap Evleri", kapadokya, "Gezi","Yerel şarap tadımı yapabileceğiniz mekanlar.", 4.5, 120);

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
        createPlace("Yahşi Plajı", bodrum, "Plaj","Daha sakin ve uzun bir sahil şeridi.", 4.6, 180);
        createPlace("Bitez Sahili", bodrum, "Plaj","Rüzgar sörfü ve deniz keyfi için ideal.", 4.7, 180);
        createPlace("Pedasa Antik Kenti", bodrum, "Tarih","Doğa yürüyüşüyle ulaşılabilen antik kalıntılar.", 4.5, 150);
        createPlace("Bodrum Amfi Tiyatro", bodrum, "Tarih","Şehir manzaralı tarihi tiyatro.", 4.4, 90);
        createPlace("Zeki Müren Sanat Müzesi", bodrum, "Müze","Ünlü sanatçının evinin müze hali.", 4.6, 90);

        // --- ROMA ---
        createPlace("Kolezyum", roma, "Tarih", "Roma'nın en ünlü amfitiyatrosu.", 5.0, 120);
        createPlace("Vatikan Müzeleri", roma, "Müze", "Michelangelo'nun eserlerinin bulunduğu dünya müzesi.", 4.9, 180);
        createPlace("Piazza Navona", roma, "Gezi", "Barok tarzı meydan.", 4.8, 90);
        createPlace("Pantheon", roma, "Tarih", "Antik Roma'nın en iyi korunmuş tapınağı.", 4.9, 60);
        createPlace("Trevi Çeşmesi", roma, "Tarih", "Dilek fıskiyesi olarak bilinen ünlü çeşme.", 4.8, 45);
        createPlace("Piazza di Spagna ve İspanyol Merdivenleri", roma, "Gezi","Ünlü merdivenler ve çevresindeki lüks mağazalar.", 4.7, 90);
        createPlace("Trastevere Mahallesi", roma, "Gezi","Dar sokakları ve restoranlarıyla ünlü tarihi mahalle.", 4.8, 150);
        createPlace("Villa Borghese Parkı", roma, "Doğa","Şehrin ortasında büyük bir park ve gölet.", 4.6, 120);
        createPlace("Galleria Borghese", roma, "Müze","Heykel ve tablolarla dolu sanat galerisi.", 4.8, 120);
        createPlace("Campo de' Fiori Pazarı", roma, "Alışveriş","Sebze, meyve ve yerel ürünlerin satıldığı pazar alanı.", 4.5, 90);

        // --- BARSELONA ---
        createPlace("Sagrada Familia", barselona, "Tarih", "Gaudi'nin yarım kalmış şaheseri.", 5.0, 120);
        createPlace("Park Güell", barselona, "Manzara", "Gaudi'nin renkli parkı.", 4.9, 120);
        createPlace("La Rambla", barselona, "Gezi", "Barselona'nın en ünlü caddesi.", 4.6, 120);
        createPlace("Barceloneta Plajı", barselona, "Plaj", "Şehrin en popüler plajı.", 4.7, 180);
        createPlace("Casa Batlló", barselona, "Tarih", "Gaudi'nin modernist eseri.", 4.8, 90);
        createPlace("Gothic Quarter (Barri Gòtic)", barselona, "Gezi","Dar sokakları ve tarihi binalarıyla eski şehir.", 4.7, 150);
        createPlace("Camp Nou Stadyumu", barselona, "Gezi","FC Barcelona'nın efsanevi stadyumu ve müzesi.", 4.7, 150);
        createPlace("Montjuïc Tepesi", barselona, "Manzara","Şehre panoramik bakış sunan tepe ve kaleler.", 4.6, 150);
        createPlace("Poble Espanyol", barselona, "Gezi","İspanya'nın farklı bölgelerinden mimari örnekler içeren açık hava müzesi.", 4.5, 120);
        createPlace("La Boqueria Pazarı", barselona, "Alışveriş","Renkli yiyecek pazarı, tapas ve deniz ürünleri.", 4.7, 90);

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
        // --- ARTVİN ---
        createPlace("Karagöl Sahara Milli Parkı", artvin, "Doğa","Göl ve orman manzaralı milli park.", 4.9, 180);
        createPlace("Borçka Karagöl", artvin, "Doğa", "Türkiye'nin en güzel göllerinden biri.", 4.9, 180);
        createPlace("Çifte Köprüler", artvin, "Tarih","Fırtına Deresi üzerindeki tarihi taş köprüler.", 4.7, 60);
        createPlace("Mençuna Şelalesi", artvin, "Doğa","Yürüyüş parkuru ile ulaşılan yüksek şelale.", 4.8, 150);
        createPlace("Artvin Kalesi", artvin, "Tarih","Şehir manzaralı tarihi kale.", 4.5, 90);
        // --- KONYA ---
        createPlace("Mevlana Müzesi", konya, "Müze","Mevlana Celaleddin Rumi'nin türbesi ve müzesi.", 4.9, 120);
        createPlace("Alaeddin Tepesi ve Camii", konya, "Tarih","Selçuklu döneminden kalma cami ve park.", 4.6, 90);
        createPlace("İnce Minare Medresesi", konya, "Tarih","Taç kapısıyla ünlü tarihi medrese.", 4.6, 90);
        createPlace("Sille Köyü", konya, "Gezi","Tarihi Rum evleri ve kiliseleriyle eski yerleşim.", 4.7, 150);
        createPlace("Kelebekler Vadisi Konya Tropikal Bahçe", konya, "Doğa","Kapalı tropikal kelebek bahçesi.", 4.5, 90);
        // --- ÇANAKKALE ---
        createPlace("Troya Antik Kenti", canakkale, "Tarih","UNESCO listesindeki efsanevi Troya şehri.", 4.8, 150);
        createPlace("Çanakkale Şehitler Abidesi", canakkale, "Tarih","Çanakkale Savaşı anısına yapılmış anıt.", 4.9, 120);
        createPlace("Kilidülbahir Kalesi", canakkale, "Tarih","Boğazı koruyan tarihi kale.", 4.6, 90);
        createPlace("Aynalı Çarşı", canakkale, "Alışveriş","Tarihi çarşı, hediyelik eşya dükkanları.", 4.5, 60);
        createPlace("Gelibolu Yarımadası Tabiat Parkı", canakkale, "Doğa","Savaş alanları ve doğa yürüyüş rotaları.", 4.7, 180);
        // --- BERLİN ---
        createPlace("Brandenburg Kapısı", berlin, "Tarih", "Berlin’in simgesi olan zafer kapısı.", 4.8, 60);
        createPlace("Reichstag Binası", berlin, "Tarih", "Alman parlamentosu ve cam kubbesiyle modern mimari harikası.", 4.9, 120);
        createPlace("Berlin Duvarı – East Side Gallery", berlin, "Tarih", "Graffitilerle kaplı Berlin Duvarı'nın en uzun korunmuş kısmı.", 4.7, 90);
        createPlace("Museum Island", berlin, "Müze", "UNESCO listesindeki müzeler kompleksi.", 5.0, 180);
        createPlace("Berlin Katedrali (Berliner Dom)", berlin, "Tarih", "Görkemli iç mimarisi ve kubbe manzarasıyla ünlü katedral.", 4.8, 90);
        createPlace("Checkpoint Charlie", berlin, "Tarih", "Soğuk Savaş döneminin en ünlü sınır kapısı.", 4.5, 60);
        createPlace("Holokost Anıtı", berlin, "Tarih", "Savaş kurbanlarına adanmış etkileyici açık hava anıtı.", 4.9, 60);
        createPlace("Tiergarten Parkı", berlin, "Doğa", "Şehrin merkezinde devasa bir doğal yürüyüş alanı.", 4.7, 120);
        createPlace("Alexanderplatz", berlin, "Gezi", "TV Kulesi, alışveriş ve meydan aktiviteleriyle Berlin’in merkezi.", 4.6, 120);
        // --- KİLİS ---
        createPlace("Ravanda Kalesi",           kilis, "Tarih",     "Vadinin hâkim noktasında yer alan tarihi Orta Çağ kalesi.",     4.7, 120);
        createPlace("Kilis Ulu Camii",          kilis, "Tarih",     "16. yüzyıldan kalma tarihi cami.",                               4.5, 60);
        createPlace("Kilis Müzesi",             kilis, "Müze",      "Bölgenin arkeolojik ve etnografik eserlerini barındırır.",       4.4, 90);
        createPlace("Tarihi Kilis Çarşısı",     kilis, "Alışveriş", "Baharatçılar, bakırcılar ve yerel lezzetlerle dolu sokaklar.",   4.6, 120);
        createPlace("Oylum Höyük",              kilis, "Tarih",     "Kilis ve çevresinin en eski yerleşim alanlarından biri.",         4.3, 90);
        createPlace("Şehitler Parkı",           kilis, "Doğa",      "Şehir merkezine yakın dinlenme ve yürüyüş alanı.",                4.2, 60);
        createPlace("Musabeyli Yaylası",        kilis, "Doğa",      "Serin havası ve piknik alanlarıyla yaz kaçamağı noktası.",        4.4, 180);
        createPlace("Katmerci Sokağı",          kilis, "Gezi",      "Kilis katmeri ve yöresel tatlılar için ünlü sokak.",              4.8, 90);
        createPlace("Cumhuriyet Meydanı",       kilis, "Gezi",      "Şehrin kalbi, kısa yürüyüşler ve fotoğraf molası için ideal.",    4.1, 45);
        // --- ATİNA ---
        createPlace("Akropolis", atina, "Tarih", 
        "Atina'nın tepesinde yer alan antik şehir kompleksi.", 5.0, 180);

        createPlace("Parthenon Tapınağı", atina, "Tarih", 
        "Tanrıça Athena'ya adanmış ikonik tapınak.", 4.9, 120);

        createPlace("Akropolis Müzesi", atina, "Müze", 
        "Dünyanın en önemli antik eser koleksiyonlarından biri.", 4.9, 150);

        createPlace("Antik Agora", atina, "Tarih", 
        "Antik dönemin siyasi ve ticari merkezi.", 4.7, 120);

        createPlace("Plaka Bölgesi", atina, "Gezi", 
        "Dar sokakları, kafeleri ve tarihi dokusuyla ünlü mahalle.", 4.6, 90);

        createPlace("Syntagma Meydanı", atina, "Gezi", 
        "Parlamento binası ve asker değişimi ile ünlü meydan.", 4.5, 60);

        createPlace("Ulusal Arkeoloji Müzesi", atina, "Müze", 
        "Antik Yunan eserlerinin en büyük koleksiyonu.", 4.8, 180);

        createPlace("Lycabettus Tepesi", atina, "Manzara", 
        "Atina'nın en iyi panoramik manzarası.", 4.7, 90);

        createPlace("Monastiraki Pazarı", atina, "Alışveriş", 
        "Hediyelik eşya, vintage ürünler ve sokak lezzetleri.", 4.5, 120);

        createPlace("Panathinaiko Stadyumu", atina, "Tarih", 
        "İlk modern olimpiyatların yapıldığı mermer stadyum.", 4.7, 60);

        createPlace("Zappeion Bahçeleri", atina, "Doğa", 
        "Şehir içinde yemyeşil yürüyüş alanı.", 4.6, 60);

        createPlace("Anafiotika Mahallesi", atina, "Gezi", 
        "Beyaz evleriyle Santorini hissi veren bölge.", 4.7, 60);

        // --- GİRİT ---
        createPlace("Knossos Sarayı", girit, "Tarih", 
        "Minos uygarlığının merkezi olan antik saray.", 4.8, 150);

        createPlace("Samaria Kanyonu", girit, "Doğa", 
        "Avrupa'nın en uzun kanyonlarından biri.", 4.9, 300);

        createPlace("Balos Plajı", girit, "Plaj", 
        "Turkuaz lagünü ile ünlü cennet plaj.", 4.9, 240);

        createPlace("Elafonisi Plajı", girit, "Plaj", 
        "Pembe kumlarıyla ünlü tropik plaj.", 4.8, 240);

        createPlace("Heraklion Arkeoloji Müzesi", girit, "Müze", 
        "Minos uygarlığına ait efsanevi eserler.", 4.9, 150);

        createPlace("Rethymno Eski Şehir", girit, "Gezi", 
        "Venedik mimarisi ve dar sokaklarıyla tarihi merkez.", 4.7, 120);

        createPlace("Chania Eski Limanı", girit, "Gezi", 
        "Tarihi liman, restoranlar ve Akdeniz manzarası.", 4.8, 90);

        createPlace("Spinalonga Adası", girit, "Tarih", 
        "Terk edilmiş kale ve eski cüzzam kolonisi.", 4.6, 180);

        createPlace("Knossos Arşivi", girit, "Müze", 
        "Arkeolojik belgelerin ve kazı materyallerinin sergilendiği müze.", 4.5, 60);

        createPlace("Preveli Manastırı", girit, "Tarih", 
        "Efsanevi hikâyeleri olan deniz kenarı manastır.", 4.7, 90);

        createPlace("Lake Kournas", girit, "Doğa", 
        "Girit’in tek doğal tatlı su gölü.", 4.6, 120);

        createPlace("Matala Plajı", girit, "Plaj", 
        "Hippi mağaraları ve turkuaz deniziyle ünlü.", 4.7, 180);

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
