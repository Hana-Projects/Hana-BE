package com.hanabridge.api.global.dto;

import com.hanabridge.api.global.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;


@Getter
@Builder
public class ApiErrorResponse {

    private int code;
    private String message;

    public static ResponseEntity<ApiErrorResponse> fromErrorCode(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(
                ApiErrorResponse.builder()
                    .code(errorCode.getHttpStatus().value())
                    .message(errorCode.getMessage())
                    .build());
    }

    public static ResponseEntity<ApiErrorResponse> fromMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return ResponseEntity.status(e.getStatusCode())
            .body(
                ApiErrorResponse.builder()
                    .code(e.getStatusCode().value())
                    .message(e.getMessage())
                    .build());
    }
}
