package com.example.driveThru.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AddOns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item_name;

    private float price;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "addOnGroup_id", referencedColumnName = "id")
    private AddOnGroup addOnGroup;
}