package com.trueguiders.service;

import com.trueguiders.model.Place;
import com.trueguiders.model.Review;
import com.trueguiders.model.User;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Transactional
    public Review addReview(Long userId, Long placeId, Integer rating, String comment) {
        User user = new User();
        user.setId(userId);
        
        Place place = placeRepository.findById(placeId)
            .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));
        
        Review review = new Review(user, place, rating, comment);
        review = reviewRepository.save(review);
        
        // Mekanın ortalama rating'ini güncelle
        updatePlaceRating(placeId);
        
        return review;
    }
    
    private void updatePlaceRating(Long placeId) {
        List<Review> reviews = reviewRepository.findByPlaceId(placeId);
        if (!reviews.isEmpty()) {
            double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
            
            Place place = placeRepository.findById(placeId).orElseThrow();
            place.setRating(avgRating);
            placeRepository.save(place);
        }
    }
    
    public List<Review> getPlaceReviews(Long placeId) {
        return reviewRepository.findByPlaceId(placeId);
    }
    
    public List<Review> getUserReviews(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}