package com.accenture.userservice.exception;

import com.accenture.userservice.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
  
  
  @ExceptionHandler(ServiceRuntimeException.class)
  public @ResponseBody ErrorDto handleServiceRuntimeException(final ServiceRuntimeException exception) {
    var errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode(exception.getErrorCode());
    log.error(exception.getStackTrace().toString());
    return errorDto;
  }
  
  @ExceptionHandler(InternalServerException.class)
  public @ResponseBody ErrorDto handleInternalServerException(final InternalServerException exception) {
    var errorDto = new ErrorDto();
    errorDto.setErrorMessage(exception.getMessage());
    errorDto.setErrorCode(exception.getErrorCode());
    log.error(exception.getStackTrace().toString());
    return errorDto;
  }

}