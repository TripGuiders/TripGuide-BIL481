package com.trueguiders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.model.City;
import com.trueguiders.model.User;
import com.trueguiders.repository.CityRepository;
import com.trueguiders.repository.TravelPlanRepository;
import com.trueguiders.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc // Sanal bir tarayıcı (Postman) gibi davranmasını sağlar
@Transactional        // Test bitince veritabanını temizler
public class TravelPlanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // İstekleri bu nesne ile atacağız

    @Autowired
    private ObjectMapper objectMapper; // Java objesini JSON'a çevirmek için

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Test
    void testCreatePlanApi_ShouldPersistToDatabase() throws Exception {
        // --- ADIM 1: Veri Hazırlığı ---
        User user = userRepository.findAll().get(0);
        City city = cityRepository.findAll().stream()
                .filter(c -> c.getName().equals("Antalya"))
                .findFirst().orElseThrow();

        // API'ye gönderilecek JSON gövdesini hazırla
        TravelPlanRequest request = new TravelPlanRequest();
        request.setUserId(user.getId());
        request.setCityId(city.getId());
        request.setDays(3);
        request.setNatureScore(5);
        request.setFunScore(2);

        // --- ADIM 2: Sanal HTTP İsteği At (POST /api/travel-plans) ---
        MvcResult result = mockMvc.perform(post("/api/travel-plans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Obje -> JSON string
                .andExpect(status().isOk()) // 200 OK döndü mü?
                .andExpect(jsonPath("$.cityName").value("Antalya")) // Dönen JSON'da şehir Antalya mı?
                .andReturn();

        // --- ADIM 3: Veritabanı Kontrolü (Persistence Check) ---
        // Veritabanına gerçekten yazıldı mı?

        // Kullanıcının son planını bulalım
        var plans = travelPlanRepository.findByUserId(user.getId());

        Assertions.assertFalse(plans.isEmpty(), "API başarılı döndü ama veritabanına kayıt atılmadı!");

        // Son eklenen planın bizim isteğimizle uyuştuğunu doğrula
        var lastPlan = plans.get(plans.size() - 1);
        Assertions.assertEquals(city.getId(), lastPlan.getCity().getId());
        Assertions.assertEquals(3, lastPlan.getDuration());

        System.out.println("✅ Controller Entegrasyon Testi Başarılı: API isteği veritabanına işlendi.");
    }
}