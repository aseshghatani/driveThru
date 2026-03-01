package com.example.driveThru.services;

import com.example.driveThru.dto.RestaurantDTO;
import com.example.driveThru.entity.Restaurant;
import com.example.driveThru.entity.RestaurantAddress;
import com.example.driveThru.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class RestaurantTransactions {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void deleteRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        restaurantRepository.delete(restaurant);
    }

    public Restaurant updateRestaurant(@PathVariable int id, @RequestBody RestaurantDTO restaurantDTO) {
        try {
            Restaurant restaurant = restaurantRepository.findById(id).get();
            restaurant.setName(restaurantDTO.getName());
            restaurant.setVegFriendly(restaurantDTO.getVegFriendly());
            restaurant.setActive(restaurantDTO.getVegFriendly());

            RestaurantAddress address = restaurant.getRestaurantAddress();
            if (address == null) {
                address = new RestaurantAddress();
                restaurant.setRestaurantAddress(address); // set bidirectional
            }

            address.setCity(restaurantDTO.getCity());
            address.setAddress(restaurantDTO.getAddress());
            address.setLandmark(restaurantDTO.getLandmark());


            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            System.out.println(e);
            return null;

        }

    }
}
