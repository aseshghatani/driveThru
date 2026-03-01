package com.example.driveThru.services;

import com.example.driveThru.dto.MenuDTO;
import com.example.driveThru.entity.Menu;
import com.example.driveThru.entity.Restaurant;
import com.example.driveThru.repository.MenuRepository;
import com.example.driveThru.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Menu> menuForRestaurant(Long id) {
        try {
            return menuRepository.findByRestaurantId(id);
        } catch (Exception e) {
            System.out.println("menu SErvice ERror:" + e);
            return Collections.emptyList();
        }


    }

    public boolean createMenu(MenuDTO menuDTO, int id) {
        try {

            Restaurant restaurant = restaurantRepository.findById(id).get();
            Menu menu = new Menu();
            menu.setName(menuDTO.getName());
            menu.setAvailable(menuDTO.getAvailable());
            menu.setVeg(menuDTO.getVeg());
            menu.setRestaurant(restaurant);
            menuRepository.save(menu);
            return true;
        } catch (Exception e) {
            System.out.println("menu Service erroe while menu creation:" + e);
            return false;
        }
    }

    public boolean updateMenu(MenuDTO menuDTO, Long id) {
        try {

            Menu menu = menuRepository.findById(id).get();
            menu.setName(menuDTO.getName());
            menu.setAvailable(menuDTO.getAvailable());
            menu.setVeg(menuDTO.getVeg());
            menuRepository.save(menu);
            return true;
        } catch (Exception e) {
            System.out.println("error while updating menu " + e);
            return false;
        }
    }


}

