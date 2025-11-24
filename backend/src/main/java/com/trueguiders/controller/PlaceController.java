// PlaceController.java
package com.trueguiders.controller;

import com.trueguiders.model.Place;
import com.trueguiders.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin(origins = "*")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.getPlaceById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Place>> getPlacesByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(placeService.getPlacesByCity(cityId));
    }

    @GetMapping("/recommendations/{cityId}")
    public ResponseEntity<List<Place>> getTopRatedPlaces(@PathVariable Long cityId) {
        return ResponseEntity.ok(placeService.getTopRatedPlaces(cityId));
    }
}