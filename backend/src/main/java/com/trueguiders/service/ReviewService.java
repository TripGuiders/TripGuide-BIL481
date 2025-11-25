package com.trueguiders.service;

import com.trueguiders.model.Place;
import com.trueguiders.model.Review;
import com.trueguiders.model.User;
import com.trueguiders.repository.PlaceRepository;
import com.trueguiders.repository.ReviewRepository;
<<<<<<< HEAD
=======
import com.trueguiders.repository.UserRepository;
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReviewService {
    
<<<<<<< HEAD
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Transactional
    public Review addReview(Long userId, Long placeId, Integer rating, String comment) {
        User user = new User();
        user.setId(userId);
=======
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private UserRepository userRepository; // Eklendi
    
    @Transactional
    public Review addReview(Long userId, Long placeId, Integer rating, String comment) {
        // Doğru yöntem: User'ı DB'den çekmek
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
        
        Place place = placeRepository.findById(placeId)
            .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));
        
        Review review = new Review(user, place, rating, comment);
        review = reviewRepository.save(review);
        
<<<<<<< HEAD
        // Mekanın ortalama rating'ini güncelle
=======
        // Mekanın ortalama puanını güncelle
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
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
            
<<<<<<< HEAD
=======
            // Rating'i virgülden sonra 1 basamak olacak şekilde yuvarlayalım (örn: 4.5)
            avgRating = Math.round(avgRating * 10.0) / 10.0;
            
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
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