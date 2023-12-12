package com.accenture.userservice.exception;

public class OrganisationDomainAlreadyExistException extends RuntimeException {
    public OrganisationDomainAlreadyExistException(final String msg) {
    super(msg);
  }
  
}