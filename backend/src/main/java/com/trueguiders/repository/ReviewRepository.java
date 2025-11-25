package com.trueguiders.repository;

<<<<<<< HEAD
public class ReviewRepository {

}
=======
import com.trueguiders.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceId(Long placeId);
    List<Review> findByUserId(Long userId);
}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
