package com.accenture.userservice.exception;

import com.accenture.userservice.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

public CustomExceptionHandler()
{

}
    @ExceptionHandler(ServiceRuntimeException.class)
    public @ResponseBody ErrorDto handleServiceRuntimeException(final ServiceRuntimeException exception) {
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(exception.getMessage());
        errorDto.setErrorCode(exception.getErrorCode());
        log.error(exception.getStackTrace().toString());
        return errorDto;
    }
//add constructor for common code errordto
    @ExceptionHandler(Throwable.class)
    public @ResponseBody ErrorDto handleNullPointerException(final Throwable exception) {
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(exception.getMessage());
       // errorDto.setErrorCode(ErrorCodeEnum.GENERAL_ERROR);
       log.error("Internal Server Error",exception);
        return errorDto;
    }
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody ErrorDto handleAuthorizationException(final AccessDeniedException exception) {
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(exception.getMessage());
        //add custom error code
        log.error(exception.getStackTrace().toString());
        return errorDto;
    }
}