package com.example.driveThru.services;

import com.example.driveThru.entity.Otp;
import com.example.driveThru.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;


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
        Optional<Otp> otp = otpRepository.findByEmail(email);

        try {

            if (otp.isPresent()) {
                Otp otpObj = otp.get();
                otpObj.setCode(String.valueOf(code));
                otpObj.setExpiresAt(LocalDateTime.now().plusMinutes(5));

                otpRepository.save(otpObj);
            } else {
                Otp otpObj = new Otp();
                otpObj.setEmail(email);
                otpObj.setCode(String.valueOf(code));
                otpObj.setExpiresAt(LocalDateTime.now().plusMinutes(5));
                otpRepository.save(otpObj);
            }


            mailService.sendMail(
                    email,
                    "OTP verification Code",
                    message

            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
