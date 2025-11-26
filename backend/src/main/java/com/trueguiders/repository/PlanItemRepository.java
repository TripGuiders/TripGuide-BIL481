package com.trueguiders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trueguiders.model.PlanItem;

@Repository
public interface PlanItemRepository extends JpaRepository<PlanItem, Long> {
    
    /**
     * Belirtilen TravelPlan ID'sine ait PlanItem'ları çeker.
     * Sonuçlar, önce gün (day) ve sonra plan içindeki sıra indeksi (orderIndex)
     * alanlarına göre sıralanır. Bu, seyahat planındaki adımları doğru sırada getirir.
     *
     * @param travelPlanId İlişkili TravelPlan'ın ID'si.
     * @return Sıralanmış PlanItem listesi.
     */
    List<PlanItem> findByTravelPlanIdOrderByDayAscOrderIndexAsc(Long travelPlanId);
}