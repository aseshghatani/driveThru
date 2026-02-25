package com.example.driveThru.repository;

import com.example.driveThru.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByEmailAndCodeAndExpiresAtAfter(
            String code,
            String email,
            LocalDateTime now
    );
}
