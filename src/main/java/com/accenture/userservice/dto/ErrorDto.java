package com.accenture.userservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
  private String errorMessage;
  @Enumerated(EnumType.STRING)
  private String errorCode;


}
