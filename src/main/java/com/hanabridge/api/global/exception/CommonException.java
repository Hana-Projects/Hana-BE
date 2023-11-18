package com.hanabridge.api.global.exception;

import com.hanabridge.api.global.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException{
    ErrorCode errorCode;
}
