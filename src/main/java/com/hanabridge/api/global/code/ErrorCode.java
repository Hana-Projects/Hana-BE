package com.hanabridge.api.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;

}
