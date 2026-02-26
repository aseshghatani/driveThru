package com.example.driveThru.controller;

import com.example.driveThru.dto.RestaurantDTO;
import com.example.driveThru.entity.Restaurant;
import com.example.driveThru.entity.RestaurantAddress;
import com.example.driveThru.repository.RestaurantAddressRepository;
import com.example.driveThru.repository.RestaurantRepository;
import com.example.driveThru.services.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantAddressRepository restaurantAddressRepo;


    @GetMapping()
    public ResponseEntity<ApiResponse<List<Restaurant>>> getRestaurant(
            @RequestParam("city") String city
    ) {
        List<Restaurant> restaurant = restaurantRepository.findRestaurantByCityInnerJoin(city);
        return ResponseEntity.status(200).body(

                new ApiResponse<>(
                        true,
                        "restaurent fetched successfully",
                        restaurant
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        RestaurantAddress restaurantAddress = new RestaurantAddress();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setVegFriendly(restaurantDTO.getVegFriendly());
        restaurantAddress.setLandmark(restaurantDTO.getLandmark());
        restaurantAddress.setAddress(restaurantDTO.getAddress());
        restaurantAddress.setCity(restaurantDTO.getCity());

        restaurantAddress.setRestaurant(restaurant);
        restaurant.setRestaurantAddress(restaurantAddress);
        restaurantAddressRepo.save(restaurantAddress);


        return ResponseEntity.status(201).body(
                new ApiResponse<>(true
                        , "successfully created restaurant",
                        restaurant

                )
        );

    }

}
