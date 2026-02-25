package com.example.driveThru.services;

import com.example.driveThru.entity.Otp;
import com.example.driveThru.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;


@Service
public class OtpService {
    private static final SecureRandom random = new SecureRandom();
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private MailService mailService;

    public void sendOtpAndMail(String email) {
        int code = 100000 + random.nextInt(900000);
        String message = "Your OTP code is " + code + ". \nIts valid for 5 mins. Don't share with anyone ";

        Otp otpObj = new Otp();
        otpObj.setEmail(email);
        otpObj.setCode(String.valueOf(code));
        otpObj.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpObj);

        mailService.sendMail(
                email,
                "OTP verification Code",
                message

        );
    }
}
