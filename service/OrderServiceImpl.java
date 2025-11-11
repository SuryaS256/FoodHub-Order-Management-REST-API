package com.example.foodhub.service;

import com.example.foodhub.dto.CreateOrderRequest;
import com.example.foodhub.dto.OrderItemRequest;
import com.example.foodhub.dto.UpdateOrderStatusRequest;
import com.example.foodhub.exception.BadRequestException;
import com.example.foodhub.exception.ResourceNotFoundException;
import com.example.foodhub.model.*;
import com.example.foodhub.repository.MenuItemRepository;
import com.example.foodhub.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerPhone(request.getCustomerPhone());
        order.setDeliveryAddress(request.getDeliveryAddress());

        double total = 0.0;

        for (OrderItemRequest itemReq : request.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found: " + itemReq.getMenuItemId()));

            if (!menuItem.isAvailable()) {
                throw new BadRequestException("Item not available: " + menuItem.getName());
            }

            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItem.getId())
                    .itemName(menuItem.getName())
                    .price(menuItem.getPrice())
                    .quantity(itemReq.getQuantity())
                    .order(order)
                    .build();

            order.getItems().add(orderItem);
            total += menuItem.getPrice() * itemReq.getQuantity();
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));
    }

    @Override
    public Order updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.DELIVERED &&
                request.getStatus() != OrderStatus.DELIVERED) {
            throw new BadRequestException("Delivered orders cannot be modified");
        }

        order.setStatus(request.getStatus());
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new BadRequestException("Cannot cancel a delivered order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
