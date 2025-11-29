package com.trueguiders.repository;

import com.trueguiders.model.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findByUserId(Long userId);
}

