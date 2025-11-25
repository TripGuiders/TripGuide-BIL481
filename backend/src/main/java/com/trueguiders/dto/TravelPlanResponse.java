package com.trueguiders.dto;

<<<<<<< HEAD
public class TravelPlanResponse {

=======
import java.util.List;
import java.util.Map;

public class TravelPlanResponse {

    private Long planId;
    private String cityName;
    private int days;
    private Map<Integer, List<ActivityDTO>> itinerary;

    public TravelPlanResponse(Long planId, String cityName, int days,
                              Map<Integer, List<ActivityDTO>> itinerary) {
        this.planId = planId;
        this.cityName = cityName;
        this.days = days;
        this.itinerary = itinerary;
    }

    public Long getPlanId() { return planId; }
    public String getCityName() { return cityName; }
    public int getDays() { return days; }
    public Map<Integer, List<ActivityDTO>> getItinerary() { return itinerary; }
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
}
