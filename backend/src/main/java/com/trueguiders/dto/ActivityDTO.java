package com.trueguiders.dto;

public class ActivityDTO {
    private Long placeId;
    private String placeName;
    private String description;
    private String startTime;
    private String endTime;
    private String category;
    private Double rating;
    
    // Constructors
    public ActivityDTO() {}
    
    public ActivityDTO(Long placeId, String placeName, String description, String startTime, 
                      String endTime, String category, Double rating) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.rating = rating;
    }
    
    // Getters and Setters
    public Long getPlaceId() { return placeId; }
    public void setPlaceId(Long placeId) { this.placeId = placeId; }
    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}