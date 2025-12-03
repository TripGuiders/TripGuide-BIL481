package com.trueguiders.repository;

import com.trueguiders.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// CityRepository.java
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);
}


