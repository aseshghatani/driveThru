package com.example.driveThru.controller;

import com.example.driveThru.entity.MailSenderDTO;
import com.example.driveThru.services.ApiResponse;
import com.example.driveThru.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/send-mail")
    public ResponseEntity<ApiResponse<?>> sendMail(@RequestBody MailSenderDTO request) {
        try {
            int code = 100000 + random.nextInt(900000);
            String message = "Your OTP code is " + code + ". Its valid for 5 mins. Don't share with anyone ";

            mailService.sendMail(
                    request.getTo(),
                    "OTP verification Sent from DriveThru",
                    message

            );
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


}
