package com.accenture.userservice.exception;

import lombok.Getter;

@Getter
public class ServiceRuntimeException extends RuntimeException {
  
  private String errorCode;
  
  public ServiceRuntimeException(String message, Enum errorCode) {
    super(message);
    this.errorCode = errorCode.toString();
  }
}
