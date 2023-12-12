package com.accenture.userservice.exception;

public class ServiceUnAvailableException extends RuntimeException{
  public ServiceUnAvailableException(final String msg) {
    super(msg);
  }
}
