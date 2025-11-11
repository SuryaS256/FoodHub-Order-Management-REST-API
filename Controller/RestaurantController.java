package com.example.foodhub.controller;

import com.example.foodhub.model.MenuItem;
import com.example.foodhub.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<MenuItem>> getMenu(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getMenuForRestaurant(restaurantId));
    }
}
