package com.example.driveThru.controller;

import com.example.driveThru.dto.UserUpdateDTO;
import com.example.driveThru.entity.User;
import com.example.driveThru.repository.UserRepository;
import com.example.driveThru.services.ApiResponse;
import com.example.driveThru.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    private JwtTokenService jwtTokenService;

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

    @GetMapping("profile")
    public ResponseEntity<User> getUserProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("profile")
    public ResponseEntity<ApiResponse<User>> updateUser(@AuthenticationPrincipal User user, @RequestBody UserUpdateDTO userUpdateDTO) {
        user.setName(userUpdateDTO.getName());
        user.setPhone(userUpdateDTO.getPhone());
        user.setIs_veg(userUpdateDTO.getIs_veg());
        userRepo.save(user);

        return ResponseEntity.status(201).body(new ApiResponse<User>(true, "updated successfully", user));
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
