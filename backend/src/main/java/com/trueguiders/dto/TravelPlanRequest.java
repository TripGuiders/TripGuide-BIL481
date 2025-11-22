package com.trueguiders.dto;

public class TravelPlanRequest {
    private String city;
    private Integer days;
    private String[] preferences; // kategoriler
    
    // Constructors
    public TravelPlanRequest() {}
    
    public TravelPlanRequest(String city, Integer days, String[] preferences) {
        this.city = city;
        this.days = days;
        this.preferences = preferences;
    }
    
    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }
    public String[] getPreferences() { return preferences; }
    public void setPreferences(String[] preferences) { this.preferences = preferences; }
}

