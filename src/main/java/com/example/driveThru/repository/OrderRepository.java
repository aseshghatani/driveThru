package com.example.driveThru.repository;


import com.example.driveThru.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderGroup, Long> {
    public List<OrderGroup> findByUserId(Long id);
}
