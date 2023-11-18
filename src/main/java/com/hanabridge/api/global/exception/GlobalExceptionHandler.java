package com.hanabridge.api.global.exception;

import com.hanabridge.api.global.dto.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDataNotFoundException(CommonException exception) {
        return ApiErrorResponse.fromErrorCode(exception.getErrorCode());
    }
}
