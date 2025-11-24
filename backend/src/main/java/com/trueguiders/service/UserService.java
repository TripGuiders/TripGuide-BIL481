package com.trueguiders.service;

import com.trueguiders.model.User;
import com.trueguiders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Yeni kullanıcı kaydı
     */
    @Transactional
    public User registerUser(String name, String email, String password) {
        // Email kontrolü
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Bu email zaten kayıtlı!");
        }
        
        // Şifre hashleme (gerçek uygulamada BCrypt kullanılmalı)
        // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User user = new User(name, email, password);
        return userRepository.save(user);
    }
    
    /**
     * Kullanıcı girişi
     */
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
        
        // Şifre kontrolü (gerçek uygulamada BCrypt.checkpw kullanılmalı)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Hatalı şifre!");
        }
        
        return user;
    }
    
    /**
     * Kullanıcı bilgilerini getir
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
    }
    
    /**
     * Email ile kullanıcı bul
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Kullanıcı bilgilerini güncelle
     */
    @Transactional
    public User updateUser(Long userId, String name, String email) {
        User user = getUserById(userId);
        
        // Eğer email değişiyorsa, başka kullanıcıda var mı kontrol et
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new RuntimeException("Bu email başka bir kullanıcı tarafından kullanılıyor!");
        }
        
        user.setName(name);
        user.setEmail(email);
        
        return userRepository.save(user);
    }
    
    /**
     * Şifre değiştir
     */
    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);
        
        // Eski şifre kontrolü
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Eski şifre hatalı!");
        }
        
        // Yeni şifreyi kaydet (gerçek uygulamada hash'lenmeli)
        user.setPassword(newPassword);
        userRepository.save(user);
    }
    
    /**
     * Kullanıcı sil
     */
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Kullanıcı bulunamadı!");
        }
        userRepository.deleteById(userId);
    }
    
    /**
     * Email kontrolü
     */
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}