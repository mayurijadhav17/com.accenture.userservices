package com.accenture.userservice.repo;

import com.accenture.userservice.model.EmailVerificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationDetailsRepository extends JpaRepository<EmailVerificationDetails, Long> {
Optional<EmailVerificationDetails> findByEmail(String email);
}
