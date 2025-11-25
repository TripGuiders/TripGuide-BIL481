package com.trueguiders.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
<<<<<<< HEAD
=======
    @JsonIgnore // şifre asla dönmesin!
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    @Column(nullable = false)
    private String password;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
<<<<<<< HEAD
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TravelPlan> travelPlans;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
    
=======

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TravelPlan> travelPlans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
<<<<<<< HEAD
    
    // Constructors
    public User() {}
    
=======

    public User() {}

>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<TravelPlan> getTravelPlans() {
        return travelPlans;
    }
    
    public void setTravelPlans(List<TravelPlan> travelPlans) {
        this.travelPlans = travelPlans;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
=======

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // password artık JSON’da görünmez (güvenlik için harika)
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<TravelPlan> getTravelPlans() { return travelPlans; }
    public void setTravelPlans(List<TravelPlan> travelPlans) { this.travelPlans = travelPlans; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
