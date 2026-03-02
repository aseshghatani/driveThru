package com.example.driveThru.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderItemDto {
    private Map<String, Object> selections;
    private Long menuId;
    private String menuName;
    private float unit_price;

    private float total_price;

    private int quantity;
}
