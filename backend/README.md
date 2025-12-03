# 🌍 TripGuide - Kişiselleştirilmiş Seyahat Planlayıcısı

**TripGuide**, gezginlerin ve öğrencilerin seyahat edecekleri şehri, kalış sürelerini ve ilgi alanlarını belirterek kendilerine özel, dakika dakika planlanmış gezi rotaları oluşturmasını sağlayan web tabanlı bir uygulamadır.

> **Takım:** TrueGuiders  
> **Durum:** MVP (Minimum Viable Product) Tamamlandı v1.0

---

## 🚀 Proje Hakkında

Bu proje, karmaşık seyahat planlama süreçlerini otomatize etmeyi amaçlar. Kullanıcılar **"Nereye gidiyorum?"**, **"Kaç gün kalacağım?"** ve **"Nelerden hoşlanırım?"** sorularını yanıtlayarak, arka planda çalışan akıllı bir algoritma sayesinde optimize edilmiş bir gezi takvimi elde ederler.

### 🌟 Temel Özellikler (Key Features)

* **Kişiselleştirilmiş Rota Oluşturma:** Şehir, gün sayısı (1-3 gün) ve ilgi alanı (Tarih, Doğa, Eğlence vb.) bazlı dinamik planlama.
* **Zaman Çizelgesi (Timeline):** Önerilen mekanların sabah, öğle ve akşam saatlerine mantıksal bir sırayla yerleştirilmesi.
* **Akıllı Filtreleme & Sıralama:** Oluşturulan rotayı kategoriye veya saate göre filtreleme imkanı.
* **Plan Kaydetme:** Oluşturulan rotaların kullanıcı profiline kaydedilmesi.
* **Değerlendirme Sistemi:** Gezilen mekanlara puan ve yorum bırakabilme.
* **Responsive Arayüz:** Farklı ekran boyutlarına uyumlu modern tasarım.

---

## 🛠️ Teknoloji Yığını (Tech Stack)

Proje, sürdürülebilirlik ve modülerlik için **Katmanlı Mimari (Layered Architecture)** kullanılarak geliştirilmiştir.

| Katman | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Frontend** | HTML5, CSS3, JavaScript | Kullanıcı arayüzü ve API iletişimi. |
| **Backend** | Java 17+, Spring Boot | RESTful API, İş mantığı ve Algoritmalar. |
| **Database** | PostgreSQL | İlişkisel veri tabanı yönetimi. |
| **Build Tool** | Maven | Bağımlılık yönetimi ve proje derleme. |
| **Testing** | JUnit 5, MockMvc | Birim ve Entegrasyon testleri. |

---

## ⚙️ Kurulum ve Çalıştırma (Installation)

Projeyi yerel ortamınızda (Localhost) çalıştırmak için aşağıdaki adımları izleyin.

### Ön Gereksinimler
* Java JDK 17 veya üzeri
* Maven
* PostgreSQL

### Adım 1: Projeyi Klonlayın
```bash
git clone [https://github.com/TrueGuiders/TripGuide.git](https://github.com/TrueGuiders/TripGuide.git)
cd TripGuide
Adım 2: Veritabanı Yapılandırması
PostgreSQL üzerinde trip_guide_db adında boş bir veritabanı oluşturun.

src/main/resources/application.properties dosyasını açın ve kendi veritabanı bilgilerinizi girin:
spring.datasource.url=jdbc:postgresql://localhost:5432/trip_guide_db
spring.datasource.username=postgres
spring.datasource.password=sifreniz
spring.jpa.hibernate.ddl-auto=update

Adım 3: Backend'i Başlatın
Terminalde proje ana dizinindeyken şu komutu çalıştırın:
mvn spring-boot:run
Tabii ki, projenin tüm dokümanlarıyla ve kod yapısıyla uyumlu, kopyalayıp doğrudan README.md dosyanıza yapıştırabileceğiniz Markdown formatındaki içerik aşağıdadır:

Markdown

# 🌍 TripGuide - Kişiselleştirilmiş Seyahat Planlayıcısı

**TripGuide**, gezginlerin ve öğrencilerin seyahat edecekleri şehri, kalış sürelerini ve ilgi alanlarını belirterek kendilerine özel, dakika dakika planlanmış gezi rotaları oluşturmasını sağlayan web tabanlı bir uygulamadır.

> **Takım:** TrueGuiders  
> **Durum:** MVP (Minimum Viable Product) Tamamlandı v1.0

---

## 🚀 Proje Hakkında

Bu proje, karmaşık seyahat planlama süreçlerini otomatize etmeyi amaçlar. Kullanıcılar **"Nereye gidiyorum?"**, **"Kaç gün kalacağım?"** ve **"Nelerden hoşlanırım?"** sorularını yanıtlayarak, arka planda çalışan akıllı bir algoritma sayesinde optimize edilmiş bir gezi takvimi elde ederler.

### 🌟 Temel Özellikler (Key Features)

* **Kişiselleştirilmiş Rota Oluşturma:** Şehir, gün sayısı (1-3 gün) ve ilgi alanı (Tarih, Doğa, Eğlence vb.) bazlı dinamik planlama.
* **Zaman Çizelgesi (Timeline):** Önerilen mekanların sabah, öğle ve akşam saatlerine mantıksal bir sırayla yerleştirilmesi.
* **Akıllı Filtreleme & Sıralama:** Oluşturulan rotayı kategoriye veya saate göre filtreleme imkanı.
* **Plan Kaydetme:** Oluşturulan rotaların kullanıcı profiline kaydedilmesi.
* **Değerlendirme Sistemi:** Gezilen mekanlara puan ve yorum bırakabilme.
* **Responsive Arayüz:** Farklı ekran boyutlarına uyumlu modern tasarım.

---

## 🛠️ Teknoloji Yığını (Tech Stack)

Proje, sürdürülebilirlik ve modülerlik için **Katmanlı Mimari (Layered Architecture)** kullanılarak geliştirilmiştir.

| Katman | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Frontend** | HTML5, CSS3, JavaScript | Kullanıcı arayüzü ve API iletişimi. |
| **Backend** | Java 17+, Spring Boot | RESTful API, İş mantığı ve Algoritmalar. |
| **Database** | PostgreSQL | İlişkisel veri tabanı yönetimi. |
| **Build Tool** | Maven | Bağımlılık yönetimi ve proje derleme. |
| **Testing** | JUnit 5, MockMvc | Birim ve Entegrasyon testleri. |

---

## ⚙️ Kurulum ve Çalıştırma (Installation)

Projeyi yerel ortamınızda (Localhost) çalıştırmak için aşağıdaki adımları izleyin.

### Ön Gereksinimler
* Java JDK 17 veya üzeri
* Maven
* PostgreSQL

### Adım 1: Projeyi Klonlayın
```bash
git clone [https://github.com/TrueGuiders/TripGuide.git](https://github.com/TrueGuiders/TripGuide.git)
cd TripGuide
Adım 2: Veritabanı Yapılandırması
PostgreSQL üzerinde trip_guide_db adında boş bir veritabanı oluşturun.

src/main/resources/application.properties dosyasını açın ve kendi veritabanı bilgilerinizi girin:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/trip_guide_db
spring.datasource.username=postgres
spring.datasource.password=sifreniz
spring.jpa.hibernate.ddl-auto=update
(Not: Proje ilk çalıştığında DataInitializer.java sayesinde demo verileri otomatik yüklenecektir.)

Adım 3: Backend'i Başlatın
Terminalde proje ana dizinindeyken şu komutu çalıştırın:

Bash

mvn spring-boot:run
Konsolda Started TrueguidersApplication yazısını gördüğünüzde sunucu http://localhost:8080 portunda çalışıyor demektir.

Adım 4: Frontend'i Başlatın
Frontend dosyaları (index.html) statik olduğu için herhangi bir modern tarayıcıda dosyayı çift tıklayarak açmanız yeterlidir.

Daha stabil bir deneyim için VS Code kullanıyorsanız "Live Server" eklentisi ile index.html dosyasını başlatabilirsiniz.

.📡 API Uç Noktaları (Endpoints)Backend ile iletişim aşağıdaki REST API uç noktaları üzerinden sağlanır:
Metot,Uç Nokta,Açıklama
GET,/api/cities,Tüm şehirleri listeler.
POST,/api/travel-plans,Tercihlere göre yeni bir rota oluşturur.
GET,/api/places/filter,Mekanları kategori ve puana göre filtreler.
POST,/api/reviews,Bir mekana puan ve yorum ekler.
GET,/api/travel-plans/user/{id},Kullanıcının geçmiş planlarını getirir.

🧪 Test Süreçleri
Proje kapsamında Birim (Unit) ve Entegrasyon testleri yazılmıştır. Testleri çalıştırmak için:
mvn test

Test Kapsamı:

UserOperationsTest: Kullanıcı kayıt/giriş işlemleri.

AlgorithmLogicTest: Rota algoritmasının mantığı ve zaman çizelgesi tutarlılığı.

PlanAndRatingTest: Plan kaydetme ve puanlama fonksiyonları.

Integration Tests: API uç noktalarının veritabanı ile iletişimi.

👥 Takım: TrueGuiders
İsim,Rol,Sorumluluk
Saadet Cansu Baktıroğlu,Project Manager (PM),"Planlama, İletişim"
İrem Keser,Full-stack Developer,"Backend, Veritabanı"
Efe Selim Sürekli,Front-end Developer,"UI Tasarımı, JS Mantığı"
Zeynep Ay,QA & Tester,"Test Senaryoları, Bug Takibi"
Elif Sema Küçük,Documentation,"Dokümantasyon, GitHub"

📄 Lisans
Bu proje eğitim amaçlı geliştirilmiştir ve açık kaynak kodludur.

© 2025 TrueGuiders - Tüm Hakları Saklıdır.
