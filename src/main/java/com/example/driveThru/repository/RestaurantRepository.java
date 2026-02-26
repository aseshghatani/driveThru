package com.example.driveThru.repository;

import com.example.driveThru.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT ra.restaurant FROM RestaurantAddress ra WHERE ra.city = :city")
    Optional<Restaurant> findRestaurantByCityInnerJoin(@Param("city") String city);
}
