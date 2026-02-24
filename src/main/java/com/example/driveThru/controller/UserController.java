package com.example.driveThru.controller;

import com.example.driveThru.entity.User;
import com.example.driveThru.repository.UserRepository;
import com.example.driveThru.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {
    @Autowired
    UserRepository userRepo;

    @GetMapping("user")
    public List<User> index() {
        return userRepo.findAll();

    }


    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {

            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("user")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User savedUser = userRepo.save(user);
        ApiResponse<User> response = new ApiResponse<>(
                true,
                "user created successfully",
                savedUser
        );
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
        ApiResponse<User> response = new ApiResponse<>(
                true,
                "User deleted successfully",
                null
        );
        return ResponseEntity.status(200).body(response);
    }
}
