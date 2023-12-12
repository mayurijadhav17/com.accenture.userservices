package com.accenture.userservice.exception;

public class UnAuthorizedException extends RuntimeException{
  public UnAuthorizedException(final String msg) {
    super(msg);
  }
}