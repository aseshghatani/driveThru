package com.example.driveThru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLogDTO {
    private String name;
    private String username;
    private String password;
}
