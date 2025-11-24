// ReviewController.java
package com.trueguiders.controller;

import com.trueguiders.model.Review;
import com.trueguiders.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Map<String, Object> reviewData) {
        Long userId = Long.parseLong(reviewData.get("userId").toString());
        Long placeId = Long.parseLong(reviewData.get("placeId").toString());
        Integer rating = Integer.parseInt(reviewData.get("rating").toString());
        String comment = reviewData.get("comment").toString();

        Review review = reviewService.addReview(userId, placeId, rating, comment);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<List<Review>> getPlaceReviews(@PathVariable Long placeId) {
        return ResponseEntity.ok(reviewService.getPlaceReviews(placeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getUserReviews(userId));
    }
}