package com.trueguiders.service;

import com.trueguiders.model.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendPlanCreatedNotification(User user, String cityName) {
        // Gerçek hayatta burada mail atılır. 
        // Proje için simülasyon yapıyoruz:
        System.out.println("=================================================");
        System.out.println("[BİLDİRİM SİMÜLASYONU] Kime: " + (user != null ? user.getEmail() : "Misafir"));
        System.out.println("Konu: Gezi Planınız Hazır!");
        System.out.println("Mesaj: Sayın gezgin, " + cityName + " için oluşturduğunuz rota hesabınıza eklendi.");
        System.out.println("=================================================");
    }
}
