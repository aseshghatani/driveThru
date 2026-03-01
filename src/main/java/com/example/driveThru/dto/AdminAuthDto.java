package com.example.driveThru.dto;

import com.example.driveThru.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AdminAuthDto {

    private String token;
    private User user;

    public AdminAuthDto(String token, Admin admin) {
        this.token = token;
        this.user = new User(admin);
    }

    @Data
    static class User {
        private String username;
        private Long id;
        private String name;
        private String role;

        public User(Admin admin) {
            this.id = admin.getId();
            this.name = admin.getName();
            this.username = admin.getUsername();
            this.role = admin.getRole().replace("ROLE_", "");
        }
    }
}
