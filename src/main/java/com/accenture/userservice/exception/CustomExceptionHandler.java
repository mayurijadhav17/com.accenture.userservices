package com.accenture.userservice.exception;

import com.accenture.userservice.dto.ErrorDto;
import com.accenture.userservice.model.ErrorCodeEnum;
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
        log.error("Service Exception", exception);
        return errorDto;
    }


    @ExceptionHandler(Throwable.class)
    public @ResponseBody ErrorDto handleNullPointerException(final Throwable exception) {
        var errorDto = new ErrorDto();
        errorDto.setErrorMessage(exception.getMessage());
        errorDto.setErrorCode(ErrorCodeEnum.SERVER_ERROR.name());
        log.error("Internal Server Error", exception);
        return errorDto;
    }

}