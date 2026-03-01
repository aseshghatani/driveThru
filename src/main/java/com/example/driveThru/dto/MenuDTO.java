package com.example.driveThru.dto;

import lombok.Data;

@Data
public class MenuDTO {
    private Long id;
    private String name;
    private boolean veg;
    private boolean available;
    private float sellingPrice;
    private float retailPrice;

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

}
