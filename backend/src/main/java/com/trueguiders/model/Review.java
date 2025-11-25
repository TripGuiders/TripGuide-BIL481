<<<<<<< HEAD
// Review.java
package com.trueguiders.model;

=======
package com.trueguiders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
<<<<<<< HEAD
=======
    @JsonIgnore
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
<<<<<<< HEAD
    private Place place;

    @Column(nullable = false)
    private Integer rating; // 1-5 arası
=======
    @JsonIgnore
    private Place place;

    @Column(nullable = false)
    private Integer rating;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432

    @Column(length = 1000)
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

<<<<<<< HEAD
    // Constructors
=======
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    public Review() {}

    public Review(User user, Place place, Integer rating, String comment) {
        this.user = user;
        this.place = place;
        this.rating = rating;
        this.comment = comment;
    }

<<<<<<< HEAD
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
=======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
