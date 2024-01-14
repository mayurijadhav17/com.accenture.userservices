package com.accenture.userservice.exception;

import lombok.Getter;

@Getter
public class ServiceRuntimeException extends RuntimeException {
  
  private String errorCode;
  
  public ServiceRuntimeException(Enum errorCode,String template,String parameter) {
    super(template+"---> "+parameter);
    this.errorCode = errorCode.toString();
  }
}
