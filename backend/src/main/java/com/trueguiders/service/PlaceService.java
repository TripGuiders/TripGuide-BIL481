// PlaceService.java
package com.trueguiders.service;

import com.trueguiders.model.Place;
import com.trueguiders.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaceService {
    
    @Autowired
    private PlaceRepository placeRepository;
    
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
    
    public List<Place> getPlacesByCity(Long cityId) {
        return placeRepository.findByCityId(cityId);
    }
    
    public List<Place> getPlacesByCityAndCategory(Long cityId, String category) {
        return placeRepository.findByCityIdAndCategory(cityId, category);
    }
    
    public Place getPlaceById(Long placeId) {
        return placeRepository.findById(placeId)
            .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));
    }
    
    public List<Place> getTopRatedPlaces(Long cityId) {
        return placeRepository.findTopRatedPlacesByCity(cityId);
    }
}
