package com.trueguiders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trueguiders.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCityId(Long cityId);
    List<Place> findByCityIdAndCategory(Long cityId, String category);
    
    @Query("SELECT p FROM Place p WHERE p.city.id = :cityId ORDER BY p.rating DESC")
    List<Place> findTopRatedPlacesByCity(Long cityId);
}