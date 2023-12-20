package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "email_verification")
public class EmailVerification {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "TOTAL_ATTEMPTS")
  private int totalAttempts;
  private int token;
  @Column(name = "EXPIRY_Date")
  private LocalDateTime expiryDate;
  @OneToOne
  private User user;
}