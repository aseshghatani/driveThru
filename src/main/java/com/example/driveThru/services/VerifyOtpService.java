package com.example.driveThru.services;

import com.example.driveThru.entity.AuthDto;
import com.example.driveThru.entity.Otp;
import com.example.driveThru.entity.User;
import com.example.driveThru.repository.OtpRepository;
import com.example.driveThru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerifyOtpService {
    private static final SecureRandom random = new SecureRandom();
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    UserRepository userRepository;

    public AuthDto verifyOtp(String email, String code) {
        int token = 10000 + random.nextInt();
        Optional<Otp> otp = otpRepository.findByEmailAndCodeAndExpiresAtAfter(
                email,
                code,
                LocalDateTime.now()

        );
        if (otp.isPresent()) {
            Optional<User> userRes = userRepository.findByEmail(email);
            if (userRes.isEmpty()) {
                User userEntity = new User();
                userEntity.setEmail(email);
                userRepository.save(userEntity);
                return new AuthDto(
                        userEntity,
                        String.valueOf(token)
                );
            } else {

                User user = userRes.get();
                return new AuthDto(
                        user,
                        String.valueOf(token)

                );

            }

        } else {
            return null;
        }
    }
}

