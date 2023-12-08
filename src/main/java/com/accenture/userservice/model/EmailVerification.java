package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "email_verification")
public class EmailVerification {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "TOTAL_ATTEMPTS")
  private Integer totalAttempts;
  private Long token;
  @Column(name = "EXPIRY_Date")
  private Date expiryDate;
  private Long userId;
}