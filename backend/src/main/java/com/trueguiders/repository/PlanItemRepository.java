package com.trueguiders.repository;

<<<<<<< HEAD
public class PlanItemRepository {

}
=======
import com.trueguiders.model.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanItemRepository extends JpaRepository<PlanItem, Long> {
    List<PlanItem> findByTravelPlanIdOrderByDayAscOrderIndexAsc(Long travelPlanId);
}
>>>>>>> 07c2d0dada063e95a870c7217eb438da75f55432
