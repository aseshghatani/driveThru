package com.example.driveThru.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item_name;

    private float price;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "variantGroup_id", referencedColumnName = "id")
    private VariantGroup variantGroup;
}
