package com.example.foodhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerPhone;

    @NotBlank
    private String deliveryAddress;

    @NotEmpty
    private List<OrderItemRequest> items;
}
