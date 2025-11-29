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
       // --- ANTALYA (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Olympos Antik Kenti", antalya, "Tarih", "Likya yolu üzerinde, doğa ve tarih iç içe.", 4.6, 180);
        createPlace("Yanartaş (Chimaera)", antalya, "Doğa", "Doğal olarak yanan sönmeyen ateş.", 4.5, 120);
        createPlace("Phaselis Antik Kenti", antalya, "Tarih", "Çam ormanları içinde antik liman ve plaj.", 4.8, 180);
        createPlace("Köprülü Kanyon", antalya, "Doğa", "Rafting ve doğa sporları merkezi.", 4.7, 240);
        createPlace("Antalya Akvaryum", antalya, "Eğlence", "Dünyanın en büyük tünel akvaryumu.", 4.3, 90);
        createPlace("Sandland", antalya, "Sanat", "Uluslararası kum heykel festivali.", 4.2, 60);
        createPlace("Karain Mağarası", antalya, "Tarih", "Anadolu'nun en eski yerleşimlerinden biri.", 4.4, 90);
        createPlace("Termessos Antik Kenti", antalya, "Tarih", "Dağ başında kartal yuvası gibi bir antik kent.", 4.8, 180);
        createPlace("Hadrian Kapısı (Üç Kapılar)", antalya, "Tarih", "Roma imparatoru adına yapılmış anıtsal kapı.", 4.6, 30);
        createPlace("Yivli Minare", antalya, "Tarih", "Şehrin simgesi olan Selçuklu eseri.", 4.5, 45);
        createPlace("Hıdırlık Kulesi", antalya, "Manzara", "Kaleiçi ve körfez manzaralı tarihi kule.", 4.4, 45);
        createPlace("Saklıkent Kayak Merkezi", antalya, "Spor", "Kışın kayak yapma imkanı sunan merkez.", 4.2, 240);
        createPlace("Adrasan Koyu", antalya, "Plaj", "Sakin ve huzurlu bir sahil.", 4.7, 180);
        createPlace("Manavgat Şelalesi", antalya, "Doğa", "Geniş bir alana dökülen ünlü şelale.", 4.3, 90);
        createPlace("Oyuncak Müzesi", antalya, "Müze", "Kaleiçi yat limanında nostaljik oyuncaklar.", 4.4, 60);

        // --- İSTANBUL (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Kız Kulesi", istanbul, "Manzara", "Boğazın ortasında tarihi kule.", 4.7, 60);
        createPlace("Süleymaniye Camii", istanbul, "Tarih", "Mimar Sinan'ın kalfalık eseri ve muhteşem manzara.", 4.9, 60);
        createPlace("İstanbul Arkeoloji Müzeleri", istanbul, "Müze", "Tarihin en önemli eserlerinin sergilendiği kompleks.", 4.8, 120);
        createPlace("Mısır Çarşısı", istanbul, "Alışveriş", "Baharat kokuları arasında tarihi çarşı.", 4.6, 60);
        createPlace("Rumeli Hisarı", istanbul, "Tarih", "Boğazın en dar noktasında tarihi hisar.", 4.5, 90);
        createPlace("Emirgan Korusu", istanbul, "Doğa", "Lale festivalleriyle ünlü tarihi park.", 4.7, 90);
        createPlace("Miniatürk", istanbul, "Eğlence", "Türkiye'deki eserlerin minyatür parkı.", 4.4, 90);
        createPlace("Rahmi M. Koç Müzesi", istanbul, "Müze", "Sanayi ve ulaşım tarihi müzesi.", 4.8, 150);
        createPlace("Ortaköy Camii ve Meydanı", istanbul, "Gezi", "Kumpir keyfi ve boğaz manzarası.", 4.6, 60);
        createPlace("Beylerbeyi Sarayı", istanbul, "Tarih", "Anadolu yakasındaki zarif yazlık saray.", 4.7, 90);
        createPlace("Gülhane Parkı", istanbul, "Doğa", "Tarihi yarımadada huzurlu bir park.", 4.5, 60);
        createPlace("İstanbul Modern", istanbul, "Müze", "Çağdaş sanat müzesi.", 4.6, 120);
        createPlace("Kariye Müzesi (Chora)", istanbul, "Tarih", "Mozaikleriyle ünlü tarihi yapı.", 4.8, 60);
        createPlace("Balat Sokakları", istanbul, "Gezi", "Renkli evler ve tarihi doku.", 4.5, 120);
        createPlace("Kadıköy Moda Sahili", istanbul, "Gezi", "Anadolu yakasının popüler yürüyüş rotası.", 4.6, 120);

        // --- İZMİR (Mevcut: 9, Eklenen: 16, Toplam: 25) ---
        createPlace("Saat Kulesi", izmir, "Tarih", "İzmir'in simgesi.", 4.7, 30);
        createPlace("Kemeraltı Çarşısı", izmir, "Alışveriş", "Tarihi çarşı.", 4.6, 120);
        createPlace("Efes Antik Kenti", izmir, "Tarih", "Dünya mirası antik kent.", 5.0, 180);
        createPlace("Kordon Boyu", izmir, "Gezi", "Deniz kenarı yürüyüş yolu.", 4.8, 90);
        createPlace("Asansör", izmir, "Manzara", "Tarihi asansör ve manzara.", 4.7, 60);
        createPlace("Şirince Köyü", izmir, "Gezi", "Tarihi Rum evleri ve şarap.", 4.6, 150);
        createPlace("Çeşme Kalesi", izmir, "Tarih", "Osmanlı kalesi ve müze.", 4.5, 60);
        createPlace("Alaçatı Sokakları", izmir, "Gezi", "Taş evler ve rüzgar sörfü.", 4.7, 120);
        createPlace("Agora Ören Yeri", izmir, "Tarih", "Antik çarşı.", 4.4, 60);
        // -- Yeni Eklenenler --
        createPlace("İzmir Doğal Yaşam Parkı", izmir, "Doğa", "Avrupa'nın en büyük doğal yaşam parklarından biri.", 4.8, 180);
        createPlace("Bergama Antik Kenti (Pergamon)", izmir, "Tarih", "UNESCO listesindeki antik kent ve akropol.", 4.9, 180);
        createPlace("Meryem Ana Evi", izmir, "Tarih", "Hristiyanlar için kutsal hac yeri.", 4.6, 60);
        createPlace("Kadifekale", izmir, "Manzara", "İzmir'i kuşbakışı gören tarihi kale.", 4.2, 60);
        createPlace("Urla Bağ Yolu", izmir, "Gezi", "Şarap tadımı ve gastronomi rotası.", 4.7, 240);
        createPlace("Key Museum", izmir, "Müze", "Geniş kapsamlı klasik otomobil müzesi.", 4.9, 90);
        createPlace("Sığacık Kaleiçi", izmir, "Gezi", "Sakin şehir (Cittaslow) Seferihisar'ın tarihi merkezi.", 4.6, 90);
        createPlace("İnciraltı Kent Ormanı", izmir, "Doğa", "Deniz kenarında geniş yeşil alan ve piknik yeri.", 4.5, 120);
        createPlace("Arkas Sanat Merkezi", izmir, "Müze", "Kordon'da süreli sanat sergileri.", 4.6, 60);
        createPlace("Aziz Polikarp Kilisesi", izmir, "Tarih", "İzmir'in en eski kilisesi.", 4.5, 45);
        createPlace("Dario Moreno Sokağı", izmir, "Gezi", "Tarihi asansörün altındaki nostaljik sokak.", 4.4, 30);
        createPlace("Hisar Camii", izmir, "Tarih", "Kemeraltı'nın en büyük ve tarihi camisi.", 4.5, 45);
        createPlace("Birgi Köyü", izmir, "Tarih", "Ödemiş'te tarihi konaklar ve Çakırağa Konağı.", 4.7, 120);
        createPlace("Foça Limanı", izmir, "Gezi", "Balıkçı tekneleri ve tarihi taş evler.", 4.6, 120);
        createPlace("Karaburun Yarımadası", izmir, "Doğa", "Bakir koylar ve nergis tarlaları.", 4.5, 180);
        createPlace("Klazomenai Antik Kenti", izmir, "Tarih", "Tarihin en eski zeytinyağı işliğinin bulunduğu yer.", 4.3, 60);

        // --- KAPADOKYA (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Güvercinlik Vadisi", kapadokya, "Doğa", "Kayalara oyulmuş güvercin yuvaları ve yürüyüş.", 4.6, 90);
        createPlace("Devrent (Hayal) Vadisi", kapadokya, "Doğa", "Hayvan şekilli peri bacaları.", 4.5, 60);
        createPlace("Kaymaklı Yeraltı Şehri", kapadokya, "Tarih", "Bölgenin en geniş yeraltı şehri.", 4.7, 90);
        createPlace("Ortahisar Kalesi", kapadokya, "Manzara", "Bölgenin en büyük peri bacalarından biri.", 4.6, 60);
        createPlace("Çavuşin Köyü", kapadokya, "Tarih", "Terk edilmiş eski Rum evleri ve kiliseler.", 4.5, 90);
        createPlace("Asmalı Konak", kapadokya, "Gezi", "Ürgüp'te ünlü diziye ev sahipliği yapmış konak.", 4.3, 45);
        createPlace("Kızılçukur Vadisi", kapadokya, "Manzara", "Gün batımının en iyi izlendiği nokta.", 4.9, 90);
        createPlace("Saç Müzesi", kapadokya, "Müze", "Avanos'ta dünyanın en ilginç müzelerinden biri.", 4.2, 30);
        createPlace("Güray Müze", kapadokya, "Müze", "Yeraltı seramik müzesi.", 4.7, 60);
        createPlace("Temenni Tepesi", kapadokya, "Manzara", "Ürgüp manzarasını izlemek için ideal tepe.", 4.4, 45);
        createPlace("Üç Güzeller", kapadokya, "Manzara", "Kapadokya'nın simgesi üçlü peri bacası.", 4.6, 30);
        createPlace("Mustafapaşa (Sinasos)", kapadokya, "Tarih", "Tarihi Rum mimarisinin en güzel örnekleri.", 4.5, 90);
        createPlace("Soğanlı Vadisi", kapadokya, "Doğa", "Bez bebekleri ve kiliseleriyle ünlü vadi.", 4.4, 120);
        createPlace("Selime Manastırı", kapadokya, "Tarih", "Ihlara vadisi çıkışında devasa kaya manastırı.", 4.7, 60);
        createPlace("Hacı Bektaş Veli Müzesi", kapadokya, "Müze", "Nevşehir'de önemli inanç merkezi.", 4.6, 90);

        // --- PARİS (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Versay Sarayı", paris, "Tarih", "Fransız krallarının görkemli sarayı ve bahçeleri.", 4.9, 240);
        createPlace("Disneyland Paris", paris, "Eğlence", "Avrupa'nın en ünlü tema parkı.", 4.8, 480);
        createPlace("Sainte-Chapelle", paris, "Tarih", "Muazzam vitray pencereleriyle ünlü şapel.", 4.8, 60);
        createPlace("Pompidou Merkezi", paris, "Müze", "Modern sanat müzesi ve ilginç mimari.", 4.5, 120);
        createPlace("Moulin Rouge", paris, "Eğlence", "Tarihi kabare ve şov merkezi.", 4.6, 120);
        createPlace("Paris Operası (Garnier)", paris, "Tarih", "Barok mimarisiyle ünlü opera binası.", 4.7, 90);
        createPlace("Pantheon", paris, "Tarih", "Fransa'nın ünlü isimlerinin anıt mezarı.", 4.5, 60);
        createPlace("Rodin Müzesi", paris, "Müze", "Düşünen Adam heykeli ve bahçesi.", 4.7, 90);
        createPlace("Tuileries Bahçesi", paris, "Doğa", "Louvre ile Concorde arasında tarihi park.", 4.6, 60);
        createPlace("Galeries Lafayette", paris, "Alışveriş", "Mimari açıdan büyüleyici alışveriş merkezi.", 4.5, 90);
        createPlace("Catacombs (Yeraltı Mezarları)", paris, "Tarih", "Paris'in altındaki kemiklerle dolu tüneller.", 4.4, 90);
        createPlace("Le Marais", paris, "Gezi", "Butikler ve tarihi sokaklarıyla ünlü semt.", 4.7, 120);
        createPlace("Concorde Meydanı", paris, "Gezi", "Dikilitaşın bulunduğu devasa meydan.", 4.5, 30);
        createPlace("Les Invalides", paris, "Tarih", "Napolyon'un mezarının bulunduğu askeri müze.", 4.6, 90);
        createPlace("Latin Mahallesi", paris, "Gezi", "Öğrenci bölgesi, kafeler ve kitapçılar.", 4.6, 120);

        // --- BODRUM (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Halikarnas Mozolesi", bodrum, "Tarih", "Dünyanın yedi harikasından birinin kalıntıları.", 4.3, 60);
        createPlace("Myndos Kapısı", bodrum, "Tarih", "Büyük İskender'in geçemediği tarihi kapı.", 4.2, 30);
        createPlace("Bodrum Yel Değirmenleri", bodrum, "Manzara", "Gümbet ve Bodrum manzaralı tarihi değirmenler.", 4.5, 45);
        createPlace("Tavşan Adası", bodrum, "Doğa", "Gümüşlük'te yürüyerek geçilebilen ada.", 4.6, 60);
        createPlace("Camel Beach (Kargı)", bodrum, "Plaj", "Develeri ve kumsalıyla ünlü plaj.", 4.5, 180);
        createPlace("Bardakçı Koyu", bodrum, "Plaj", "Merkeze yakın, efsanelere konu olmuş koy.", 4.6, 120);
        createPlace("Akyarlar", bodrum, "Plaj", "Berrak denizi ve kum plajı.", 4.8, 180);
        createPlace("Osmanlı Tersanesi", bodrum, "Tarih", "Tarihi tersane ve sanat galerisi.", 4.4, 45);
        createPlace("Dibeklihan Kültür ve Sanat Köyü", bodrum, "Gezi", "Sanat galerileri ve butik dükkanlar.", 4.7, 120);
        createPlace("Iasos Antik Kenti", bodrum, "Tarih", "Kıyıkışlacık'ta sakin bir antik kent.", 4.5, 90);
        createPlace("Yalıkavak Marina", bodrum, "Alışveriş", "Lüks mağazalar ve restoranlar.", 4.8, 120);
        createPlace("Aspat Koyu", bodrum, "Doğa", "Venedik kalesi kalıntıları ve plaj.", 4.6, 150);
        createPlace("Karaada", bodrum, "Doğa", "Tekne turlarının uğrak noktası, sıcak su mağarası.", 4.5, 90);
        createPlace("Mazı Köyü", bodrum, "Doğa", "Doğayla iç içe sakin koylar.", 4.7, 180);
        createPlace("Bargilya Antik Kenti", bodrum, "Tarih", "Tuzla sulak alanı kuş cenneti ve antik kalıntılar.", 4.4, 90);

        // --- ROMA (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Roma Forumu", roma, "Tarih", "Antik Roma'nın politik ve sosyal merkezi.", 4.8, 120);
        createPlace("Aziz Petrus Bazilikası", roma, "Tarih", "Vatikan'daki devasa ve görkemli kilise.", 4.9, 90);
        createPlace("Castel Sant'Angelo", roma, "Tarih", "Tiber nehri kıyısında tarihi kale ve müze.", 4.7, 90);
        createPlace("Palatino Tepesi", roma, "Tarih", "Roma'nın yedi tepesinden biri, imparatorluk sarayları.", 4.6, 90);
        createPlace("Capitoline Müzeleri", roma, "Müze", "Dünyanın en eski halk müzelerinden biri.", 4.7, 120);
        createPlace("Vittorio Emanuele II Abidesi", roma, "Manzara", "Roma'nın beyaz mermerden devasa anıtı.", 4.6, 60);
        createPlace("Piazza del Popolo", roma, "Gezi", "Halk meydanı ve ikiz kiliseler.", 4.5, 45);
        createPlace("Santa Maria Maggiore Bazilikası", roma, "Tarih", "Roma'nın en büyük Meryem Ana kilisesi.", 4.7, 60);
        createPlace("Gerçeğin Ağzı (Bocca della Verità)", roma, "Tarih", "Efsanevi taş maske.", 4.2, 30);
        createPlace("Caracalla Hamamları", roma, "Tarih", "Antik Roma'nın devasa halk hamamları.", 4.6, 90);
        createPlace("Via del Corso", roma, "Alışveriş", "Roma'nın ana alışveriş caddesi.", 4.4, 120);
        createPlace("Circus Maximus", roma, "Tarih", "Antik araba yarışı stadyumu alanı.", 4.3, 45);
        createPlace("Janiculum Tepesi (Gianicolo)", roma, "Manzara", "Şehrin en iyi panoramik manzaralarından biri.", 4.7, 60);
        createPlace("Kapuçin Kriptası", roma, "Tarih", "Kemiklerle dekore edilmiş ilginç şapel.", 4.4, 45);
        createPlace("Orange Garden (Giardino degli Aranci)", roma, "Doğa", "Portakal ağaçları ve güzel bir şehir manzarası.", 4.6, 60);

        // --- BARSELONA (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Casa Milà (La Pedrera)", barselona, "Tarih", "Gaudi'nin bir başka ikonik yapısı.", 4.7, 90);
        createPlace("Picasso Müzesi", barselona, "Müze", "Picasso'nun eserlerinin sergilendiği müze.", 4.6, 120);
        createPlace("Barselona Katedrali", barselona, "Tarih", "Gotik mahalledeki görkemli katedral.", 4.6, 60);
        createPlace("Ciutadella Parkı", barselona, "Doğa", "Şelalesi ve göletiyle ünlü şehir parkı.", 4.6, 90);
        createPlace("Palau de la Música Catalana", barselona, "Sanat", "Modernist mimarinin zirvesi konser salonu.", 4.8, 60);
        createPlace("Tibidabo", barselona, "Eğlence", "Şehre tepeden bakan lunapark ve kilise.", 4.5, 180);
        createPlace("MNAC (Ulusal Sanat Müzesi)", barselona, "Müze", "Katalan sanatının sergilendiği dev saray.", 4.7, 120);
        createPlace("Sihirli Çeşme (Magic Fountain)", barselona, "Eğlence", "Müzikli ve ışıklı su gösterisi.", 4.8, 60);
        createPlace("Arc de Triomf", barselona, "Tarih", "Kızıl tuğlalı zafer takı.", 4.5, 30);
        createPlace("Barselona Akvaryumu", barselona, "Eğlence", "Liman bölgesinde büyük akvaryum.", 4.4, 90);
        createPlace("Columbus Anıtı", barselona, "Manzara", "La Rambla sonunda deniz kenarındaki anıt.", 4.3, 30);
        createPlace("Gracia Mahallesi", barselona, "Gezi", "Bohem atmosferi ve meydanlarıyla ünlü semt.", 4.6, 120);
        createPlace("Santa Maria del Mar", barselona, "Tarih", "Halkın inşa ettiği gotik bazilika.", 4.7, 45);
        createPlace("Hospital de Sant Pau", barselona, "Tarih", "Art Nouveau tarzı eski hastane kompleksi.", 4.6, 90);
        createPlace("Bunkers del Carmel", barselona, "Manzara", "Şehrin en iyi 360 derece manzarası.", 4.8, 90);

        // --- LONDRA (Mevcut: 10, Eklenen: 15, Toplam: 25) ---
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
        // -- Yeni Eklenenler --
        createPlace("Westminster Abbey", londra, "Tarih", "Kraliyet düğünlerinin yapıldığı tarihi kilise.", 4.7, 90);
        createPlace("Big Ben ve Parlamento", londra, "Tarih", "Londra'nın dünyaca ünlü saat kulesi.", 4.8, 45);
        createPlace("Tate Modern", londra, "Müze", "Eski bir elektrik santralinde modern sanat.", 4.6, 120);
        createPlace("The Shard", londra, "Manzara", "Batı Avrupa'nın en yüksek binasından manzara.", 4.7, 60);
        createPlace("Tower of London", londra, "Tarih", "Kraliyet mücevherlerinin saklandığı tarihi kale.", 4.6, 150);
        createPlace("Covent Garden", londra, "Gezi", "Sokak sanatçıları ve mağazalarla dolu meydan.", 4.7, 120);
        createPlace("Borough Market", londra, "Alışveriş", "Londra'nın en ünlü gıda pazarı.", 4.8, 90);
        createPlace("Victoria and Albert Museum", londra, "Müze", "Dünyanın en büyük tasarım ve sanat müzesi.", 4.7, 150);
        createPlace("Sky Garden", londra, "Manzara", "Gökdelenin tepesinde ücretsiz halka açık bahçe.", 4.8, 60);
        createPlace("Trafalgar Meydanı", londra, "Gezi", "Aslan heykelleri ve Ulusal Galeri'nin olduğu meydan.", 4.6, 45);
        createPlace("Harrods", londra, "Alışveriş", "Dünyaca ünlü lüks mağaza.", 4.5, 120);
        createPlace("Shakespeare's Globe", londra, "Sanat", "Tarihi tiyatronun aslına uygun rekonstrüksiyonu.", 4.6, 90);
        createPlace("Kensington Sarayı", londra, "Tarih", "Kraliyet ailesi üyelerinin yaşadığı saray.", 4.5, 90);
        createPlace("Regent's Park", londra, "Doğa", "Güzel bahçeleri ve açık hava tiyatrosu olan park.", 4.6, 90);
        createPlace("Science Museum", londra, "Müze", "Bilim ve teknoloji meraklıları için müze.", 4.7, 120);

        // --- ARTVİN (Mevcut: 5, Eklenen: 20, Toplam: 25) ---
        createPlace("Karagöl Sahara Milli Parkı", artvin, "Doğa","Göl ve orman manzaralı milli park.", 4.9, 180);
        createPlace("Borçka Karagöl", artvin, "Doğa", "Türkiye'nin en güzel göllerinden biri.", 4.9, 180);
        createPlace("Çifte Köprüler", artvin, "Tarih","Fırtına Deresi üzerindeki tarihi taş köprüler.", 4.7, 60);
        createPlace("Mençuna Şelalesi", artvin, "Doğa","Yürüyüş parkuru ile ulaşılan yüksek şelale.", 4.8, 150);
        createPlace("Artvin Kalesi", artvin, "Tarih","Şehir manzaralı tarihi kale.", 4.5, 90);
        // -- Yeni Eklenenler --
        createPlace("Macahel (Camili) Vadisi", artvin, "Doğa", "UNESCO korumasındaki biyosfer rezerv alanı.", 5.0, 300);
        createPlace("Maral Şelalesi", artvin, "Doğa", "Tek kırımda dökülen heybetli şelale.", 4.8, 120);
        createPlace("Cehennem Deresi Kanyonu", artvin, "Doğa", "Dünyanın en derin kanyonlarından biri.", 4.7, 120);
        createPlace("Hatila Vadisi Milli Parkı", artvin, "Doğa", "Cam teras ve zengin bitki örtüsü.", 4.8, 150);
        createPlace("Gorgit Yaylası", artvin, "Doğa", "Araç yolu olmayan, bakir kalmış yayla.", 4.9, 240);
        createPlace("Şavşat Karagöl", artvin, "Doğa", "Şavşat ilçesinde masalsı bir göl.", 4.9, 150);
        createPlace("Atatepe", artvin, "Manzara", "Dünyanın en büyük Atatürk heykeli ve şehir manzarası.", 4.6, 60);
        createPlace("İşhan Kilisesi", artvin, "Tarih", "Orta çağdan kalma tarihi manastır kilisesi.", 4.5, 60);
        createPlace("Tibeti Kilisesi", artvin, "Tarih", "Şavşat'ta tarihi kilise kalıntısı.", 4.4, 45);
        createPlace("Barhal (Altıparmak) Kilisesi", artvin, "Tarih", "Yusufeli'nde sağlam kalmış tarihi kilise.", 4.6, 60);
        createPlace("Beyazsu Yaylası", artvin, "Doğa", "Kaçkar dağlarının eteklerinde muazzam yayla.", 4.8, 180);
        createPlace("Arsiyan Yaylası", artvin, "Doğa", "Göller bölgesi ve efsaneleriyle ünlü yayla.", 4.8, 240);
        createPlace("Satlel Kalesi", artvin, "Tarih", "Şavşat'ta tarihi kale.", 4.4, 45);
        createPlace("Kafkasör Yaylası", artvin, "Eğlence", "Boğa güreşlerinin yapıldığı ünlü yayla.", 4.5, 120);
        createPlace("Bilbilan Yaylası", artvin, "Doğa", "Yüksek rakımlı ve serin yayla.", 4.6, 120);
        createPlace("Deliklikaya Şelalesi", artvin, "Doğa", "Kayanın içinden akan ilginç şelale.", 4.7, 60);
        createPlace("Dolishane Kilisesi", artvin, "Tarih", "Ardanuç'ta tarihi yapı.", 4.3, 45);
        createPlace("Ferhatlı Kalesi", artvin, "Tarih", "Ardanuç'ta sarp kayalık üzerindeki kale.", 4.4, 45);
        createPlace("Balık Gölü", artvin, "Doğa", "Şavşat Pınarlı köyünde huzurlu bir göl.", 4.7, 90);
        createPlace("Yusufeli Baraj Gölü", artvin, "Manzara", "Yeni oluşan devasa baraj gölü manzarası.", 4.5, 60);

        // --- KONYA (Mevcut: 5, Eklenen: 20, Toplam: 25) ---
        createPlace("Mevlana Müzesi", konya, "Müze","Mevlana Celaleddin Rumi'nin türbesi ve müzesi.", 4.9, 120);
        createPlace("Alaeddin Tepesi ve Camii", konya, "Tarih","Selçuklu döneminden kalma cami ve park.", 4.6, 90);
        createPlace("İnce Minare Medresesi", konya, "Tarih","Taç kapısıyla ünlü tarihi medrese.", 4.6, 90);
        createPlace("Sille Köyü", konya, "Gezi","Tarihi Rum evleri ve kiliseleriyle eski yerleşim.", 4.7, 150);
        createPlace("Kelebekler Vadisi Konya Tropikal Bahçe", konya, "Doğa","Kapalı tropikal kelebek bahçesi.", 4.5, 90);
        // -- Yeni Eklenenler --
        createPlace("Çatalhöyük Neolitik Kenti", konya, "Tarih", "İnsanlık tarihinin en eski yerleşimlerinden biri.", 4.8, 120);
        createPlace("Karatay Medresesi", konya, "Müze", "Çini eserler müzesi olarak kullanılan Selçuklu yapısı.", 4.7, 60);
        createPlace("Şems-i Tebrizi Türbesi", konya, "Tarih", "Mevlana'nın can dostunun türbesi.", 4.8, 45);
        createPlace("Konya Bilim Merkezi", konya, "Eğlence", "Türkiye'nin TÜBİTAK destekli ilk bilim merkezi.", 4.6, 180);
        createPlace("Meram Bağları", konya, "Doğa", "Tarihi mesire yeri ve yeşil alan.", 4.5, 120);
        createPlace("Japon Parkı", konya, "Doğa", "Kyoto tarzı peyzajı ile huzurlu bir park.", 4.4, 60);
        createPlace("Aziziye Camii", konya, "Tarih", "Barok ve Rokoko tarzı pencereleriyle ünlü cami.", 4.6, 45);
        createPlace("Beyşehir Gölü", konya, "Doğa", "Türkiye'nin en büyük tatlı su gölü.", 4.7, 180);
        createPlace("Eşrefoğlu Camii", konya, "Tarih", "Ahşap direkli camilerin en büyüğü ve güzeli (Beyşehir).", 4.9, 60);
        createPlace("Kilistra Antik Kenti", konya, "Tarih", "Kapadokya benzeri kaya oluşumları ve yerleşim.", 4.5, 120);
        createPlace("Nasreddin Hoca Türbesi", konya, "Tarih", "Akşehir'de ünlü mizah ustasının türbesi.", 4.6, 60);
        createPlace("Bedesten Çarşısı", konya, "Alışveriş", "Tarihi çarşı ve antikacılar.", 4.4, 90);
        createPlace("Meke Krater Gölü", konya, "Doğa", "Dünyanın nazar boncuğu (kuraklık riski olsa da önemli).", 4.3, 60);
        createPlace("Yerköprü Şelalesi", konya, "Doğa", "Hadim ilçesinde muazzam bir doğa harikası.", 4.8, 150);
        createPlace("Tuz Gölü", konya, "Doğa", "Bembeyaz sonsuzluk hissi veren göl.", 4.6, 90);
        createPlace("Sahip Ata Müzesi", konya, "Müze", "Selçuklu mimarisi ve tarihi eserler.", 4.5, 60);
        createPlace("Atatürk Evi Müzesi", konya, "Müze", "Atatürk'ün Konya ziyaretlerinde kaldığı ev.", 4.4, 45);
        createPlace("Arkeoloji Müzesi", konya, "Müze", "Bölgeden çıkan antik eserler.", 4.5, 90);
        createPlace("Ecdad Parkı", konya, "Gezi", "Osmanlı ve Selçuklu mimarisiyle tasarlanmış büyük park.", 4.5, 90);
        createPlace("80 Binde Devr-i Alem Parkı", konya, "Eğlence", "Dinozor maketleri ve masal kahramanları parkı.", 4.4, 90);

        // --- ÇANAKKALE (Mevcut: 5, Eklenen: 20, Toplam: 25) ---
        createPlace("Troya Antik Kenti", canakkale, "Tarih","UNESCO listesindeki efsanevi Troya şehri.", 4.8, 150);
        createPlace("Çanakkale Şehitler Abidesi", canakkale, "Tarih","Çanakkale Savaşı anısına yapılmış anıt.", 4.9, 120);
        createPlace("Kilidülbahir Kalesi", canakkale, "Tarih","Boğazı koruyan tarihi kale.", 4.6, 90);
        createPlace("Aynalı Çarşı", canakkale, "Alışveriş","Tarihi çarşı, hediyelik eşya dükkanları.", 4.5, 60);
        createPlace("Gelibolu Yarımadası Tabiat Parkı", canakkale, "Doğa","Savaş alanları ve doğa yürüyüş rotaları.", 4.7, 180);
        // -- Yeni Eklenenler --
        createPlace("Troya Müzesi", canakkale, "Müze", "Ödüllü modern müze, Troya hazineleri.", 4.9, 120);
        createPlace("Assos Antik Kenti (Behramkale)", canakkale, "Tarih", "Athena Tapınağı ve muhteşem manzara.", 4.8, 120);
        createPlace("Bozcaada Sokakları", canakkale, "Gezi", "Renkli Rum evleri ve ada atmosferi.", 4.8, 180);
        createPlace("Ayazma Plajı", canakkale, "Plaj", "Bozcaada'nın buz gibi ama berrak denizi.", 4.7, 180);
        createPlace("Gökçeada (İmroz)", canakkale, "Doğa", "Sakin köyler ve doğal yaşam.", 4.6, 240);
        createPlace("57. Alay Şehitliği", canakkale, "Tarih", "Anzak koyuna hakim şehitlik.", 4.9, 60);
        createPlace("Conkbayırı", canakkale, "Tarih", "Atatürk'ün saatinin parçalandığı tarihi tepe.", 4.8, 60);
        createPlace("Seyit Onbaşı Anıtı", canakkale, "Tarih", "Kahraman topçunun anıtı.", 4.9, 30);
        createPlace("Çimenlik Kalesi ve Deniz Müzesi", canakkale, "Müze", "Nusret Mayın Gemisi'nin replikasının olduğu yer.", 4.7, 90);
        createPlace("Korfmann Kütüphanesi", canakkale, "Gezi", "Tarihi bir binada arkeoloji kütüphanesi.", 4.5, 30);
        createPlace("Saat Kulesi", canakkale, "Tarih", "Şehir merkezindeki buluşma noktası.", 4.4, 30);
        createPlace("Parion Antik Kenti", canakkale, "Tarih", "Biga yakınlarında önemli kazı alanı.", 4.3, 90);
        createPlace("Alexandria Troas Antik Kenti", canakkale, "Tarih", "Büyük bir antik liman kenti kalıntıları.", 4.4, 90);
        createPlace("Apollon Smintheus Tapınağı", canakkale, "Tarih", "Gülpınar'da farelerin efendisi Apollon tapınağı.", 4.5, 60);
        createPlace("Babakale", canakkale, "Gezi", "Asya kıtasının en batı ucu ve kalesi.", 4.6, 60);
        createPlace("Kaz Dağları (İda)", canakkale, "Doğa", "Mitolojik dağ, şelaleler ve temiz hava.", 4.8, 240);
        createPlace("Hasanboğuldu Göleti", canakkale, "Doğa", "Sütüven şelalesi ve piknik alanı.", 4.7, 120);
        createPlace("Yeşilyurt Köyü", canakkale, "Gezi", "Kaz Dağları eteklerinde taş evleriyle ünlü köy.", 4.6, 90);
        createPlace("Adatepe Köyü ve Zeus Altarı", canakkale, "Manzara", "Körfez manzaralı tarihi köy ve sunak.", 4.7, 90);
        createPlace("Seramik Müzesi", canakkale, "Müze", "Geleneksel Çanakkale seramikleri.", 4.4, 45);

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
