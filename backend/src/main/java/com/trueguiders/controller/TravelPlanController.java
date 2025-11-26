package com.trueguiders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.service.TravelPlanService;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin(origins = "*") // Frontend ile iletişim için
public class TravelPlanController {
    
    @Autowired
    private TravelPlanService travelPlanService;
    
    /**
     * Yeni seyahat planı oluştur
     * POST /api/plans/create
     * Body: { "city": "antalya", "days": 3, "preferences": ["müze", "turistik"] }
     */
    @PostMapping("/create")
    public ResponseEntity<TravelPlanResponse> createPlan(@RequestBody TravelPlanRequest request) {
        try {
            // Demo için userId=1 kullanılıyor (gerçek uygulamada session/JWT'den alınmalı)
            TravelPlanResponse response = travelPlanService.createTravelPlan(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }
    
    /**
     * Kullanıcının tüm planlarını listele
     * GET /api/plans/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TravelPlanResponse>> getUserPlans(@PathVariable Long userId) {
        List<TravelPlanResponse> plans = travelPlanService.getUserPlans(userId);
        return ResponseEntity.ok(plans);
    }
    
    /**
     * Belirli bir planın detaylarını getir
     * GET /api/plans/{planId}
     */
    @GetMapping("/{planId}")
    public ResponseEntity<TravelPlanResponse> getPlanDetails(@PathVariable Long planId) {
        try {
            TravelPlanResponse response = travelPlanService.getPlanDetails(planId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    /**
     * Planı sil
     * DELETE /api/plans/{planId}
     */
    @DeleteMapping("/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId) {
        try {
            // Silme işlemi eklenecek
            return ResponseEntity.ok("Plan silindi");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Plan bulunamadı");
        }
    }
}