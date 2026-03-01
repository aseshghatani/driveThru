package com.example.driveThru.controller;

import com.example.driveThru.dto.MenuDTO;
import com.example.driveThru.entity.AddOnGroup;
import com.example.driveThru.entity.Menu;
import com.example.driveThru.repository.AddOnGroupRespository;
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
    @Autowired
    AddOnGroupRespository addOnGroupRespository;

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

    @PostMapping("/create/{id}")
    public ResponseEntity<ApiResponse<?>> createMenu(@PathVariable int id, @RequestBody MenuDTO menuDTO) {
        if (menuService.createMenu(menuDTO, id)) {
            return ResponseEntity.status(201).body(new ApiResponse<>(
                    true,
                    "menu created successfully",
                    null
            ));
        } else {
            return ResponseEntity.status(501).body(new ApiResponse<>(
                    false,
                    "failed creating menu",
                    null
            ));
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteMenu(@PathVariable Long id) {
        try {
            menuRepository.deleteById(id);
            return ResponseEntity.status(200).body(
                    new ApiResponse<>(
                            true,
                            "Deleted REsord successsfully",
                            null
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(501).body(
                    new ApiResponse<>(
                            false,
                            "Deleted REsord successsfully",
                            null
                    ));
        }

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<?>> updateMenu(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {
        if (menuService.updateMenu(menuDTO, id)) {
            return ResponseEntity.status(201).body(new ApiResponse<>(
                    true,
                    "updated menu successfully",
                    null
            ));
        } else {
            return ResponseEntity.status(501).body(
                    new ApiResponse<>(
                            false,
                            "failed while updating menu item",
                            null
                    )
            );
        }
    }

    @GetMapping("/variant/{id}")
    public ResponseEntity<ApiResponse<?>> getMenu(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id).get();
        return ResponseEntity.status(200).body(new ApiResponse<>(
                true,
                "menu fetched successfully",
                menu
        ));
    }

    @DeleteMapping("/add-on-group/delete/{id}")
    public Void deleteAddonGroup(@PathVariable Long id) {
        try {
            addOnGroupRespository.deleteById(id);
        } catch (Exception e) {
            System.out.println("error while deleteing addOn group" + e);
        }
        return null;
    }


}
