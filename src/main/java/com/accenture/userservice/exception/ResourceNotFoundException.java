package com.accenture.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String msg) {
  super(msg);
 }

}