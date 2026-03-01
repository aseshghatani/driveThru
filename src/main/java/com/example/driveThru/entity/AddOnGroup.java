package com.example.driveThru.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOnGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean multiple_select;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;
    @OneToMany(mappedBy = "addOnGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AddOns> addOns;

}
