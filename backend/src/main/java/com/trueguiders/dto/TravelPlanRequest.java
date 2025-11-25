package com.trueguiders.dto;

public class TravelPlanRequest {

<<<<<<< HEAD
}
=======
    private Long userId;
    private Long cityId;
    private Integer days; // Artık tarih yok, sadece gün sayısı var
    private String[] preferences;

    public TravelPlanRequest() {}

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }

    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }

    public String[] getPreferences() { return preferences; }
    public void setPreferences(String[] preferences) { this.preferences = preferences; }
}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
