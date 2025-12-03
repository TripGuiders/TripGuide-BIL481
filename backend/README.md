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
