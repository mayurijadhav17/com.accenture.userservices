package com.accenture.userservice.repo;

import com.accenture.userservice.model.EmailVerificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationDetailsRepository extends JpaRepository<EmailVerificationDetails, Long> {
boolean existsByEmail(String email);
EmailVerificationDetails findByEmail(String email);
}
