package com.example.driveThru.repository;

import com.example.driveThru.entity.AddOns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddOnRepository extends JpaRepository<AddOns, Long> {
}
