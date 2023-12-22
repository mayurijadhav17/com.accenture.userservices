package com.accenture.userservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ErrorDto {
  private String errorMessage;
  @Enumerated(EnumType.STRING)
  private String errorCode;
}
