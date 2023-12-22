package com.accenture.userservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EmailVerificationDto {
  @Enumerated(EnumType.STRING)
  private Enum errorCode;
  private int remainingAttempts;
}
