package com.trueguiders.dto;

public class TravelPlanRequest {

    private Long userId;
    private Long cityId;
    private Integer days; 
    private String[] preferences; // İstersen kullanmaya devam edebiliriz

    // 5 üzerinden kategori puanları
    private Integer funScore;      // eğlence / lunapark
    private Integer cultureScore;  // tarih / müze
    private Integer natureScore;   // doğa / manzara
    private Integer beachScore;    // plaj
    private Integer shoppingScore; // alışveriş

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

    public Integer getFunScore() { return funScore; }
    public void setFunScore(Integer funScore) { this.funScore = funScore; }

    public Integer getCultureScore() { return cultureScore; }
    public void setCultureScore(Integer cultureScore) { this.cultureScore = cultureScore; }

    public Integer getNatureScore() { return natureScore; }
    public void setNatureScore(Integer natureScore) { this.natureScore = natureScore; }

    public Integer getBeachScore() { return beachScore; }
    public void setBeachScore(Integer beachScore) { this.beachScore = beachScore; }

    public Integer getShoppingScore() { return shoppingScore; }
    public void setShoppingScore(Integer shoppingScore) { this.shoppingScore = shoppingScore; }
}
