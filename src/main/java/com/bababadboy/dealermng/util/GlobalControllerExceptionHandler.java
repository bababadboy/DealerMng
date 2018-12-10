package com.bababadboy.dealermng.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author Ash
 * @date 2018/11/29 10:59
 */
//@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestServiceError handleValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : errors) {
            stringBuilder.append(violation.getMessage() + "\n");
        }
        return RestServiceError.build(RestServiceError.Type.VALIDATION_ERROR, stringBuilder.toString());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestServiceError handleException(Exception ex) {
        return RestServiceError.build(RestServiceError.Type.INTERNAL_SERVER_ERROR, ex.getMessage());
    }



}
