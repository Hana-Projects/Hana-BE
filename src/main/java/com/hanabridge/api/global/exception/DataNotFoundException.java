package com.hanabridge.api.global.exception;

import com.hanabridge.api.global.code.ErrorCode;
import lombok.Getter;


@Getter
public class DataNotFoundException extends CommonException{
    public DataNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}
