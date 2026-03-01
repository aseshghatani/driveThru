package com.example.driveThru.controller;

import com.example.driveThru.entity.Menu;
import com.example.driveThru.repository.MenuRepository;
import com.example.driveThru.services.ApiResponse;
import com.example.driveThru.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getMenuFor(@PathVariable Long id) {
        List<Menu> menu = menuService.menuForRestaurant(id);
        if (menu == null || menu.isEmpty()) {
            System.out.println("Refer To MenuService Exception");

            return ResponseEntity.status(404).body(new ApiResponse<>(
                    false,
                    "no menu found",
                    null
            ));
        } else {
            return ResponseEntity.status(200).body(new ApiResponse<>(
                    true,
                    "Menu Fetched successfully",
                    menu
            ));
        }

    }

}
