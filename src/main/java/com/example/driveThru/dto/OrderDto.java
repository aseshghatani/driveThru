package com.example.driveThru.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long order_number;
    private float total_amount;
    private int item_count;
    private List<OrderItemDto> orderItems;
}
