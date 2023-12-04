package com.hanabridge.api.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 username의 고객이 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "일치하지 않는 비밀번호입니다."),
    BANK_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "일치하는 은행 코드가 존재하지 않습니다."),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 계좌가 없습니다."),
    BALANCE_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "잔액이 충분하지 않습니다."),
    OPENAPI_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "open api 호출 시 에러 발생하였습니다."),
    HOME_INFO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "불러온 청약 정보가 존재하지 않습니다."),
    GUIDE_NOT_FOUND(HttpStatus.BAD_REQUEST, "해석할 수 없는 음성입니다. 음성을 다시 입력하세요."),
    VOICE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 음성 코드가 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;

}
