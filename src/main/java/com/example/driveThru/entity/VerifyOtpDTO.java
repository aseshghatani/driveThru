package com.example.driveThru.entity;

import java.time.LocalDateTime;

public class VerifyOtpDTO {
    private String code;
    private String mail;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
