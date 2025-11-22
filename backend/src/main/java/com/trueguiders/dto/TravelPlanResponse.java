package com.trueguiders.dto;

import java.util.List;
import java.util.Map;

public class TravelPlanResponse {
    private Long planId;
    private String city;
    private Integer totalDays;
    private Map<Integer, List<ActivityDTO>> dailyItinerary; // gün -> aktiviteler
    
    // Constructors
    public TravelPlanResponse() {}
    
    public TravelPlanResponse(Long planId, String city, Integer totalDays, Map<Integer, List<ActivityDTO>> dailyItinerary) {
        this.planId = planId;
        this.city = city;
        this.totalDays = totalDays;
        this.dailyItinerary = dailyItinerary;
    }
    
    // Getters and Setters
    public Long getPlanId() { return planId; }
    public void setPlanId(Long planId) { this.planId = planId; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Integer getTotalDays() { return totalDays; }
    public void setTotalDays(Integer totalDays) { this.totalDays = totalDays; }
    public Map<Integer, List<ActivityDTO>> getDailyItinerary() { return dailyItinerary; }
    public void setDailyItinerary(Map<Integer, List<ActivityDTO>> dailyItinerary) { 
        this.dailyItinerary = dailyItinerary; 
    }
}
