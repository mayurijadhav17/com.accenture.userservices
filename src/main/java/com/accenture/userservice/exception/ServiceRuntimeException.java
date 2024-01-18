package com.accenture.userservice.exception;

import com.accenture.userservice.model.ErrorCodeEnum;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class ServiceRuntimeException extends RuntimeException {
  
  private String errorCode;

  public ServiceRuntimeException(ErrorCodeEnum errorCode, Object... parameter) {
    super(MessageFormat.format(errorCode.getTemplate(),parameter));
    this.errorCode = errorCode.toString();
  }
}
