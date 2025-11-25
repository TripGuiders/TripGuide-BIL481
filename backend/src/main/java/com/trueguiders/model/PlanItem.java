package com.trueguiders.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
import jakarta.persistence.*;

@Entity
@Table(name = "plan_items")
public class PlanItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
<<<<<<< HEAD
=======
    @JsonIgnore
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    private TravelPlan travelPlan;
    
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
<<<<<<< HEAD
    private Place place;
    
    @Column(nullable = false)
    private Integer day; // kaçıncı gün
    
    @Column(name = "start_time")
    private String startTime; // örn: "09:00"
    
    @Column(name = "end_time")
    private String endTime; // örn: "11:00"
    
    @Column(name = "order_index")
    private Integer orderIndex; // gün içinde sıralama
    
    // Constructors
    public PlanItem() {}
    
=======
    // Place üzerinde @JsonIgnore YOK (Frontend görsün diye)
    private Place place;
    
    @Column(nullable = false)
    private Integer day;
    
    @Column(name = "start_time")
    private String startTime;
    
    @Column(name = "end_time")
    private String endTime;
    
    @Column(name = "order_index")
    private Integer orderIndex;
    
    // 1. Boş Constructor (JPA için gerekli)
    public PlanItem() {}
    
    // 2. DOLU CONSTRUCTOR (SERVİSİN KULLANDIĞI BU - EKSİK OLAN BUYDU)
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    public PlanItem(TravelPlan travelPlan, Place place, Integer day, String startTime, String endTime, Integer orderIndex) {
        this.travelPlan = travelPlan;
        this.place = place;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderIndex = orderIndex;
    }
    
    // Getters and Setters
<<<<<<< HEAD
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public TravelPlan getTravelPlan() {
        return travelPlan;
    }
    
    public void setTravelPlan(TravelPlan travelPlan) {
        this.travelPlan = travelPlan;
    }
    
    public Place getPlace() {
        return place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }
    
    public Integer getDay() {
        return day;
    }
    
    public void setDay(Integer day) {
        this.day = day;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public Integer getOrderIndex() {
        return orderIndex;
    }
    
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
=======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TravelPlan getTravelPlan() { return travelPlan; }
    public void setTravelPlan(TravelPlan travelPlan) { this.travelPlan = travelPlan; }

    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }

    public Integer getDay() { return day; }
    public void setDay(Integer day) { this.day = day; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
}