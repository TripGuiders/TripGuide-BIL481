package com.trueguiders.repository;

import com.trueguiders.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    // Şehirdeki tüm mekanlar
    List<Place> findByCityId(Long cityId);

    // Şehir + tek kategori arama
    List<Place> findByCityIdAndCategory(Long cityId, String category);

    // Şehir + çoklu kategori arama (TERCİH SİSTEMİ İÇİN EN ÖNEMLİ OLAN BU)
    List<Place> findByCityIdAndCategoryIn(Long cityId, List<String> categories);

    // En yüksek puanlı mekanlar
    @Query("SELECT p FROM Place p WHERE p.city.id = :cityId ORDER BY p.rating DESC")
    List<Place> findTopRatedPlacesByCity(Long cityId);
}
