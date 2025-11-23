package com.trueguiders.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Place> places;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TravelPlan> travelPlans;

    public City() {}

    public City(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Place> getPlaces() { return places; }
    public void setPlaces(List<Place> places) { this.places = places; }

    public List<TravelPlan> getTravelPlans() { return travelPlans; }
    public void setTravelPlans(List<TravelPlan> travelPlans) { this.travelPlans = travelPlans; }
}
