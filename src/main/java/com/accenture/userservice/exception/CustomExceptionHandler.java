package com.accenture.userservice.exception;

import com.accenture.userservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {
  ErrorDto errorDto;
  
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorDto handleResourceNotFound(final UserNotFoundException exception) {
    errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode("USER_NOT_FOUND");
    return errorDto;
  }
  
  @ExceptionHandler(OrganisationNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorDto handleOrganisationNotFound(final OrganisationNotFoundException exception) {
    errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode("ORGANISATION_NOT_FOUND");
    return errorDto;
  }
  
  @ExceptionHandler(EMailAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public @ResponseBody ErrorDto handleEMailAlreadyExist(final EMailAlreadyExistException exception) {
    errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode("EMAIL_EXISTS");
    return errorDto;
  }
  
  @ExceptionHandler(OrganisationDomainAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
   public @ResponseBody ErrorDto handleOrganisationDomainAlreadyExist(final OrganisationDomainAlreadyExistException exception) {
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode("ORGANISATION_EXISTS");
    return errorDto;
  }
}