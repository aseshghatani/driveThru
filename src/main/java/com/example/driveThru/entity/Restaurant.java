package com.example.driveThru.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "isVeg")
    private boolean VegFriendly;
    @Column(name = "isActive", columnDefinition = "boolean default true")

    private boolean active;

    @Column(nullable = true)
    private int likes;
    @Column(nullable = true)
    private int dislikes;
    @OneToOne(mappedBy = "restaurant")
    @JsonManagedReference
    private RestaurantAddress restaurantAddress;
}
