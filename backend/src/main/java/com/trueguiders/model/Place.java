package com.trueguiders.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "places")
public class Place {

<<<<<<< HEAD
    


    
=======
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
<<<<<<< HEAD
    private City city;
    
    @Column(nullable = false)
    private String category; // şehir merkezi, turistik, müze, eğlence parkı
=======
    @JsonIgnore
    private City city;
    
    @Column(nullable = false)
    private String category; 
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    
    private String description;
    
    @Column(nullable = false)
    private Double rating = 0.0;
    
<<<<<<< HEAD
    @Column(name = "visit_duration") // dakika cinsinden
=======
    @Column(name = "visit_duration")
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    private Integer visitDuration;
    
    @Column(name = "opening_time")
    private String openingTime;
    
    @Column(name = "closing_time")
    private String closingTime;
    
    private String address;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
<<<<<<< HEAD
    private List<PlanItem> planItems;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews;
    
    // Constructors
    public Place() {}
    
=======
    @JsonIgnore
    private List<PlanItem> planItems;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;
    
    public Place() {}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432

    public Place(String name, City city, String category, String description, Integer visitDuration) {
        this.name = name;
        this.city = city;
        this.category = category;
        this.description = description;
        this.visitDuration = visitDuration;
    }
<<<<<<< HEAD
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(Integer visitDuration) {
        this.visitDuration = visitDuration;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<PlanItem> getPlanItems() {
        return planItems;
    }

    public void setPlanItems(List<PlanItem> planItems) {
        this.planItems = planItems;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
=======

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getVisitDuration() { return visitDuration; }
    public void setVisitDuration(Integer visitDuration) { this.visitDuration = visitDuration; }

    public String getOpeningTime() { return openingTime; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

    public String getClosingTime() { return closingTime; }
    public void setClosingTime(String closingTime) { this.closingTime = closingTime; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<PlanItem> getPlanItems() { return planItems; }
    public void setPlanItems(List<PlanItem> planItems) { this.planItems = planItems; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
