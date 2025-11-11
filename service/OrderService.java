package com.example.foodhub.service;

import com.example.foodhub.dto.CreateOrderRequest;
import com.example.foodhub.dto.UpdateOrderStatusRequest;
import com.example.foodhub.model.Order;

public interface OrderService {

    Order createOrder(CreateOrderRequest request);

    Order getOrderById(Long orderId);

    Order updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    void cancelOrder(Long orderId);
}
