package com.example.driveThru.controller;


import com.example.driveThru.dto.AdminAuthDto;
import com.example.driveThru.dto.AdminLogDTO;
import com.example.driveThru.dto.AuthDto;
import com.example.driveThru.entity.Admin;
import com.example.driveThru.repository.AdminRepository;
import com.example.driveThru.services.AdminService;

import com.example.driveThru.services.ApiResponse;
import com.example.driveThru.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin")
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLogDTO adminLogDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        adminLogDTO.getUsername(),
                        adminLogDTO.getPassword()
                )
        );
        Admin admin = (Admin) authentication.getPrincipal();
        String token = jwtTokenService.GenerateAdminToken(admin);
        AdminAuthDto response = new AdminAuthDto(token, admin);
        return ResponseEntity.status(200).body(new ApiResponse<>(
                true,
                "user successfully authenticated",
                response
        ));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminLogDTO adminLogDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminLogDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminLogDTO.getPassword()));
        admin.setName(adminLogDTO.getName());
        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.status(201).body(new ApiResponse<>(
                true,
                "Admin Created Successfully",
                savedAdmin
        ));
    }
}
