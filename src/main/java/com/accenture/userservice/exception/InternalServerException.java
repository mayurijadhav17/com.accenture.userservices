package com.accenture.userservice.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {
  
  private String errorCode;
  
  public InternalServerException(String message, Enum errorCode) {
    super(message);
    this.errorCode = errorCode.toString();
  }
}
