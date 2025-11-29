// ContactController.java
package com.trueguiders.controller;

import com.trueguiders.dto.ContactRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @PostMapping
    public ResponseEntity<Map<String, String>> submitContactForm(@RequestBody ContactRequest request) {
        // Gerçek uygulamada email gönderme servisi çağrılacak
        System.out.println("İletişim formu alındı:");
        System.out.println("Ad: " + request.getName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Konu: " + request.getSubject());
        System.out.println("Mesaj: " + request.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Mesajınız başarıyla gönderildi!");

        return ResponseEntity.ok(response);
    }
}