package com.example.driveThru.repository;

import com.example.driveThru.entity.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Long> {
    @Query("SELECT DISTINCT ra.city FROM RestaurantAddress ra")
    List<String> findDistinctCities();
}
