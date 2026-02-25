package com.example.driveThru.services;

import com.example.driveThru.dto.AuthDto;
import com.example.driveThru.entity.Otp;
import com.example.driveThru.entity.User;
import com.example.driveThru.repository.OtpRepository;
import com.example.driveThru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JwtTokenService jwtTokenService;

    public AuthDto verifyOtp(String email, String code) {

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

                otpRepository.delete(otp.get());
                String token = jwtTokenService.accessToken(userEntity);
                return new AuthDto(
                        userEntity,
                        token
                );
            } else {

                User user = userRes.get();
                otpRepository.delete(otp.get());
                String token = jwtTokenService.accessToken(user);

                return new AuthDto(
                        user,
                        token

                );

            }

        } else {
            return null;
        }
    }
}

