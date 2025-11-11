package com.example.foodhub.dto;

import com.example.foodhub.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    @NotNull
    private OrderStatus status;
}
