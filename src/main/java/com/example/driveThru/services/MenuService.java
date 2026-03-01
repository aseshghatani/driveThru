package com.example.driveThru.services;

import com.example.driveThru.entity.Menu;
import com.example.driveThru.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> menuForRestaurant(Long id) {
        try {
            return menuRepository.findByRestaurantId(id);
        } catch (Exception e) {
            System.out.println("menu SErvice ERror:" + e);
            return Collections.emptyList();
        }


    }
}
