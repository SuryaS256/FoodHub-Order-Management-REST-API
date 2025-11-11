package com.example.foodhub.service;

import com.example.foodhub.model.MenuItem;

import java.util.List;

public interface RestaurantService {
    List<MenuItem> getMenuForRestaurant(Long restaurantId);
}
