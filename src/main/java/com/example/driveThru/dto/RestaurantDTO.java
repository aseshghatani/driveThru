package com.example.driveThru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String name;
    private String city;
    private Long restaurant_id;


    private String address;
    private boolean vegFriendly;
    private String landmark;

    public boolean getVegFriendly() {
        return vegFriendly;
    }

    public void setVegFriendly() {
        this.vegFriendly = vegFriendly;
    }
}
