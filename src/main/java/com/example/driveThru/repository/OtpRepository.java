package com.example.driveThru.repository;

import com.example.driveThru.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
}
