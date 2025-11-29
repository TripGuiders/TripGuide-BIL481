package com.trueguiders.service;

import com.trueguiders.model.Place;
import com.trueguiders.model.Review;
import com.trueguiders.model.User;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.ReviewRepository;
import com.trueguiders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReviewService {
    
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private UserRepository userRepository; // Eklendi
    
    @Transactional
    public Review addReview(Long userId, Long placeId, Integer rating, String comment) {
        // Doğru yöntem: User'ı DB'den çekmek
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        
        Place place = placeRepository.findById(placeId)
            .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));
        
        Review review = new Review(user, place, rating, comment);
        review = reviewRepository.save(review);
        
        // Mekanın ortalama puanını güncelle
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
            
            // Rating'i virgülden sonra 1 basamak olacak şekilde yuvarlayalım (örn: 4.5)
            avgRating = Math.round(avgRating * 10.0) / 10.0;
            
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