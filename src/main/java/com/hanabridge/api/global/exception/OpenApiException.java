package com.hanabridge.api.global.exception;

import com.hanabridge.api.global.code.ErrorCode;
import lombok.Getter;

@Getter
public class OpenApiException extends CommonException{
    public OpenApiException(ErrorCode errorCode){
        super(errorCode);
    }
}
