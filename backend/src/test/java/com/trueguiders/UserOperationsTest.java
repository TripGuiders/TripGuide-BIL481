package com.trueguiders;

import com.trueguiders.model.User;
import com.trueguiders.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
// User database'e kaydediliyor.
public class UserOperationsTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserRegistrationAndLogin() {
        // 1. Kayıt Senaryosu
        User newUser = new User();
        newUser.setName("Test Kullanıcısı2");
        newUser.setEmail("test2@demo.com");
        newUser.setPassword("1234567");

        userRepository.save(newUser);

        // 2. Giriş Kontrolü (Email ile bulma)
        User foundUser = userRepository.findByEmail("test2@demo.com").orElse(null);

        // 3. Doğrulamalar
        Assertions.assertNotNull(foundUser, "Kullanıcı veritabanına kaydedilemedi!");
        Assertions.assertEquals("Test Kullanıcısı2", foundUser.getName());
        Assertions.assertEquals("1234567", foundUser.getPassword(), "Şifre eşleşmedi!");

        System.out.println("Kullanıcı Kayıt/Giriş Testi Başarılı");
    }
}