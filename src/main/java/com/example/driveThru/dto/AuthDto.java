package com.example.driveThru.dto;

import com.example.driveThru.entity.User;

public class AuthDto {

    private String token;
    private User user;

    public AuthDto(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
