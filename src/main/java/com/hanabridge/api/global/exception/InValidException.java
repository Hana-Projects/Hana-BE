package com.hanabridge.api.global.exception;

import com.hanabridge.api.global.code.ErrorCode;
import lombok.Getter;

@Getter
public class InValidException extends CommonException {

    public InValidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
