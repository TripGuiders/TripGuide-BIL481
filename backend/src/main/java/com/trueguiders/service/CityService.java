// CityService.java
package com.trueguiders.service;

import com.trueguiders.model.City;
import com.trueguiders.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;
    
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    
    public City getCityByName(String name) {
        return cityRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Şehir bulunamadı: " + name));
    }
    
    public City getCityById(Long id) {
        return cityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Şehir bulunamadı"));
    }
}