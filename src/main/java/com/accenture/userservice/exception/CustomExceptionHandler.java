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

  
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorDto handleResourceNotFound(final ResourceNotFoundException exception) {
    errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode(HttpStatus.NOT_FOUND.value());
    return errorDto;
  }
  
  @ExceptionHandler(EMailAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public @ResponseBody ErrorDto handleResourceNotFound(final EMailAlreadyExistException exception) {
    errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode(HttpStatus.CONFLICT.value()); //409 error code for conflict
    return errorDto;
  }
  
  @ExceptionHandler(OrganisationDomainAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
   public @ResponseBody ErrorDto handleResourceNotFound(final OrganisationDomainAlreadyExistException exception) {
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode(HttpStatus.CONFLICT.value());
    return errorDto;
  }
}