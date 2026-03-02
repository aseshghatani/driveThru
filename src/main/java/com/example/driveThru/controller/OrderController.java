package com.example.driveThru.controller;

import com.example.driveThru.dto.OrderDto;

import com.example.driveThru.entity.OrderGroup;
import com.example.driveThru.entity.OrderItem;
import com.example.driveThru.entity.OrderItem;
import com.example.driveThru.entity.User;
import com.example.driveThru.repository.OrderItemsRepository;
import com.example.driveThru.repository.OrderRepository;
import com.example.driveThru.repository.UserRepository;
import com.example.driveThru.services.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    OrderRepository OrderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List>> getOrder(@PathVariable Long id) {
        List<OrderGroup> Orders = OrderRepository.findByUserId(id);
        if (Orders.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    false,
                    "no Order for user found",
                    null
            ));
        } else {
            return ResponseEntity.status(200).body(new ApiResponse<>(
                    true,
                    "successfullly Order fetched for user",
                    Orders
            ));
        }
    }

    public Long generateOrderNumber() {
        // Option 1: Timestamp (14 digits)
        return System.currentTimeMillis();
        // 1709451234567
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<ApiResponse<?>> saveOrder(@PathVariable int id, @RequestBody OrderDto OrderDto) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    false,
                    "No user found to create Order for",
                    null
            ));
        } else {
            try {
                OrderGroup Order = new OrderGroup();
                Order.setUser(user.get());
                Order.setOrder_number(generateOrderNumber());
                Order.setItem_count(OrderDto.getItem_count());
                Order.setTotal_amount(OrderDto.getTotal_amount());
                List<OrderItem> OrderItems = OrderDto.getOrderItems().stream()
                        .map(dtoItem -> {
                            OrderItem entity = new OrderItem();
                            entity.setMenu_id(dtoItem.getMenuId());
                            entity.setMenu_name(dtoItem.getMenuName());
                            entity.setQuantity(dtoItem.getQuantity());
                            entity.setSelections(dtoItem.getSelections());
                            entity.setTotal_price(dtoItem.getTotal_price());
                            entity.setUnit_price(dtoItem.getUnit_price());
                            entity.setOrderGroup(Order);  // Link back!
                            return entity;
                        })
                        .toList();
                Order.setOrderItems(OrderItems);
                OrderGroup OrderSaved = OrderRepository.save(Order);

                return ResponseEntity.status(201).body(new ApiResponse<>(
                        true,
                        "Order Created Successfully",
                        OrderSaved
                ));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

}
