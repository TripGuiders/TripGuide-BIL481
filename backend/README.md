# 🌍 TripGuide - Kişiselleştirilmiş Seyahat Planlayıcısı

**TripGuide**, gezginlerin ve öğrencilerin seyahat edecekleri şehri, kalış sürelerini ve ilgi alanlarını belirterek kendilerine özel, dakika dakika planlanmış gezi rotaları oluşturmasını sağlayan web tabanlı bir uygulamadır.

> **Takım:** TrueGuiders  
> **Durum:** MVP (Minimum Viable Product) – v1.0 Tamamlandı

---

## 🚀 Proje Hakkında

Bu proje, karmaşık seyahat planlama süreçlerini otomatize etmeyi amaçlar. Kullanıcılar:

- **"Nereye gidiyorum?"**
- **"Kaç gün kalacağım?"**
- **"Nelerden hoşlanırım?"**

sorularını yanıtlayarak, arka planda çalışan akıllı bir algoritma sayesinde optimize edilmiş bir gezi takvimi elde ederler.

---

## 🌟 Temel Özellikler (Key Features)

- **Kişiselleştirilmiş Rota Oluşturma:** Şehir, gün sayısı (1–3 gün) ve ilgi alanı (Tarih, Doğa, Eğlence vb.) bazlı dinamik planlama.
- **Zaman Çizelgesi (Timeline):** Önerilen mekanların sabah, öğle ve akşam saatlerine mantıksal bir sırayla yerleştirilmesi.
- **Akıllı Filtreleme & Sıralama:** Oluşturulan rotayı kategoriye veya saate göre filtreleme imkanı.
- **Plan Kaydetme:** Oluşturulan rotaların kullanıcı profiline kaydedilmesi.
- **Değerlendirme Sistemi:** Gezilen mekanlara puan ve yorum bırakabilme.
- **Responsive Arayüz:** Farklı ekran boyutlarına uyumlu modern tasarım.

---

## 🛠️ Teknoloji Yığını (Tech Stack)

Proje, sürdürülebilirlik ve modülerlik için **Katmanlı Mimari (Layered Architecture)** kullanılarak geliştirilmiştir.

| Katman | Teknoloji | Açıklama |
|--------|-----------|----------|
| Frontend | HTML5, CSS3, JavaScript | Kullanıcı arayüzü ve API iletişimi |
| Backend | Java 17+, Spring Boot | RESTful API, iş mantığı ve algoritmalar |
| Database | PostgreSQL | İlişkisel veritabanı yönetimi |
| Build Tool | Maven | Bağımlılık yönetimi ve derleme |
| Testing | JUnit 5, MockMvc | Birim ve entegrasyon testleri |

---

## ⚙️ Kurulum ve Çalıştırma (Installation)

Projeyi yerel ortamınızda (**Localhost**) çalıştırmak için aşağıdaki adımları izleyin.

---

### ✅ Ön Gereksinimler

- Java JDK 17 veya üzeri
- Maven
- PostgreSQL

---

### 🔹 Adım 1: Projeyi Klonlayın

git clone https://github.com/TrueGuiders/TripGuide.git  
cd TripGuide

---

### 🔹 Adım 2: Veritabanı Yapılandırması

PostgreSQL üzerinde **trip_guide_db** adında boş bir veritabanı oluşturun.

`src/main/resources/application.properties` dosyasını açın ve aşağıdaki bilgileri girin:

spring.datasource.url=jdbc:postgresql://localhost:5432/trip_guide_db  
spring.datasource.username=postgres  
spring.datasource.password=sifreniz  
spring.jpa.hibernate.ddl-auto=update  

> Not: Proje ilk çalıştığında `DataInitializer.java` sayesinde demo veriler otomatik olarak yüklenecektir.

---

### 🔹 Adım 3: Backend’i Başlatın

mvn spring-boot:run

Konsolda `Started TrueguidersApplication` gördüğünüzde:

http://localhost:8080 adresinde aktif olur.

---

### 🔹 Adım 4: Frontend’i Başlatın

- `index.html` dosyasını çift tıklayarak açabilirsiniz.
- VS Code kullanıyorsanız **Live Server** ile daha stabil çalıştırabilirsiniz.

---

## 📡 API Uç Noktaları (Endpoints)

| Metot | Uç Nokta | Açıklama |
|--------|----------|----------|
| GET | /api/cities | Tüm şehirleri listeler |
| POST | /api/travel-plans | Yeni rota oluşturur |
| GET | /api/places/filter | Mekanları filtreler |
| POST | /api/reviews | Puan ve yorum ekler |
| GET | /api/travel-plans/user/{id} | Kullanıcının planları |

---

## 🧪 Test Süreçleri

Testleri çalıştırmak için:

mvn test

### Test Kapsamı

- UserOperationsTest → Kullanıcı işlemleri  
- AlgorithmLogicTest → Rota algoritması  
- PlanAndRatingTest → Plan & puanlama  
- Integration Tests → API – DB bağlantısı  

---

## 👥 Takım: TrueGuiders

| İsim | Rol | Sorumluluk |
|------|-----|------------|
| Saadet Cansu Baktıroğlu | Project Manager | Planlama, İletişim |
| İrem Keser | Full-stack Developer | Backend, Veritabanı |
| Efe Selim Sürekli | Front-end Developer | UI, JavaScript |
| Zeynep Ay | QA & Tester | Test, Bug Takibi |
| Elif Sema Küçük | Documentation | Dokümantasyon, GitHub |

---

## 📄 Lisans

Bu proje **eğitim amaçlı** geliştirilmiştir ve açık kaynak kodludur.

---

© 2025 **TrueGuiders** – Tüm Hakları Saklıdır.
