package com.trueguiders.repository;

import com.trueguiders.model.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanItemRepository extends JpaRepository<PlanItem, Long> {
    List<PlanItem> findByTravelPlanIdOrderByDayAscOrderIndexAsc(Long travelPlanId);
}
