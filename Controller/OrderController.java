package com.example.foodhub.controller;

import com.example.foodhub.dto.CreateOrderRequest;
import com.example.foodhub.dto.UpdateOrderStatusRequest;
import com.example.foodhub.model.Order;
import com.example.foodhub.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Order created = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId,
                                              @Valid @RequestBody UpdateOrderStatusRequest request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
