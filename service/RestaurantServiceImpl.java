package com.example.foodhub.service;

import com.example.foodhub.exception.ResourceNotFoundException;
import com.example.foodhub.model.MenuItem;
import com.example.foodhub.repository.MenuItemRepository;
import com.example.foodhub.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getMenuForRestaurant(Long restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + restaurantId));
        return menuItemRepository.findByRestaurantIdAndAvailableTrue(restaurantId);
    }
}
