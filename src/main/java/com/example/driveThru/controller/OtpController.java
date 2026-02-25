package com.example.driveThru.controller;

import com.example.driveThru.dto.AuthDto;
import com.example.driveThru.dto.MailSenderDTO;
import com.example.driveThru.dto.VerifyOtpDTO;
import com.example.driveThru.repository.OtpRepository;
import com.example.driveThru.repository.UserRepository;
import com.example.driveThru.services.ApiResponse;
import com.example.driveThru.services.MailService;
import com.example.driveThru.services.OtpService;
import com.example.driveThru.services.VerifyOtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@RequestMapping
public class OtpController {

    private static final Logger log = LoggerFactory.getLogger(OtpController.class);
    private static final SecureRandom random = new SecureRandom();
    @Autowired
    MailService mailService;
    @Autowired
    OtpService otpService;
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    UserRepository userRepo;
    @Autowired
    VerifyOtpService verifyOtpService;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<?>> sendOtp(@RequestBody MailSenderDTO request) {
        try {
            otpService.sendOtpAndMail(request.getTo());
            ApiResponse<?> response = new ApiResponse<>(
                    true,
                    "mail sent Successfullly",
                    null

            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            ApiResponse<?> response = new ApiResponse<>(
                    false,
                    "failed sending message",
                    null
            );
            return ResponseEntity.status(500).body(response);

        }


    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<?>> verifyOtp(@RequestBody VerifyOtpDTO request) {
        AuthDto response = verifyOtpService.verifyOtp(
                request.getMail(),
                request.getCode()
        );
        if (response != null) {
            return ResponseEntity.status(201).body(new ApiResponse<>(
                    true,
                    "authorized successfully",
                    response
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            "error occured",
                            null
                    )
            );
        }
    }

}
