package com.trueguiders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "travel_plans")
public class TravelPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City city;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String preferences;

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlanItem> planItems;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public TravelPlan() {}

    public TravelPlan(User user, City city, Integer duration, LocalDate startDate) {
        this.user = user;
        this.city = city;
        this.duration = duration;
        this.startDate = startDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }

    public List<PlanItem> getPlanItems() { return planItems; }
    public void setPlanItems(List<PlanItem> planItems) { this.planItems = planItems; }
}