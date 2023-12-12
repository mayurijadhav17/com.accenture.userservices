package com.accenture.userservice.exception;

public class EMailAlreadyExistException extends RuntimeException {
    public EMailAlreadyExistException(final String msg) {
    super(msg);
  }
  
}