package com.trueguiders.controller;

import com.trueguiders.dto.*;
import com.trueguiders.model.User;
import com.trueguiders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Kullanıcı kaydı
     * POST /api/users/register
     * Body: { "name": "Ali Yılmaz", "email": "ali@example.com", "password": "123456" }
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            User user = userService.registerUser(
                    request.getName(),
                    request.getEmail(),
                    request.getPassword()
            );

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Kayıt başarılı!");
            result.put("user", response);

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Kullanıcı girişi
     * POST /api/users/login
     * Body: { "email": "ali@example.com", "password": "123456" }
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            User user = userService.loginUser(
                    request.getEmail(),
                    request.getPassword()
            );

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Giriş başarılı!");
            result.put("user", response);

            // Gerçek uygulamada JWT token dönülmeli
            // result.put("token", jwtService.generateToken(user));

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    /**
     * Kullanıcı bilgilerini getir
     * GET /api/users/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Kullanıcı bilgilerini güncelle
     * PUT /api/users/{userId}
     * Body: { "name": "Ali Yılmaz", "email": "yeniali@example.com" }
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long userId,
            @RequestBody Map<String, String> updates) {
        try {
            User user = userService.updateUser(
                    userId,
                    updates.get("name"),
                    updates.get("email")
            );

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Bilgiler güncellendi!");
            result.put("user", response);

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Şifre değiştir
     * PUT /api/users/{userId}/password
     * Body: { "oldPassword": "123456", "newPassword": "654321" }
     */
    @PutMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(
            @PathVariable Long userId,
            @RequestBody PasswordChangeRequest request) {
        try {
            userService.changePassword(
                    userId,
                    request.getOldPassword(),
                    request.getNewPassword()
            );

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Şifre başarıyla değiştirildi!");

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Kullanıcı sil
     * DELETE /api/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Kullanıcı silindi!");

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Email kontrolü
     * GET /api/users/check-email?email=test@example.com
     */
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailExists(email);

        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        return ResponseEntity.ok(result);
    }
}