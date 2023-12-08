package com.accenture.userservice.repo;

import com.accenture.userservice.model.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

Optional<EmailVerification> findByUserId(Long id);
}
