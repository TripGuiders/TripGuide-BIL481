package com.trueguiders.controller;

import com.trueguiders.dto.TravelPlanRequest;
import com.trueguiders.dto.TravelPlanResponse;
import com.trueguiders.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-plans")
@CrossOrigin(origins = "*")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelPlanService;

    @PostMapping
    public ResponseEntity<TravelPlanResponse> createPlan(@RequestBody TravelPlanRequest request) {
        // userId artık request'in içinde geliyor
        return ResponseEntity.ok(travelPlanService.createTravelPlan(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TravelPlanResponse>> getUserPlans(@PathVariable Long userId) {
        return ResponseEntity.ok(travelPlanService.getUserPlans(userId));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<TravelPlanResponse> getPlanById(@PathVariable Long planId) {
        return ResponseEntity.ok(travelPlanService.getPlanDetails(planId));
    }
}