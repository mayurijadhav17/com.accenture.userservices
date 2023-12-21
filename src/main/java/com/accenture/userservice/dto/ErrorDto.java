package com.accenture.userservice.dto;

import lombok.Data;

@Data
public class ErrorDto {
  private String errorMessage;
  private String errorCode;
}
