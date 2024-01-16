package com.hanabridge.api.transaction.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum RemitCode {
    ACCOUNT, PHONE, NOTDECIDE;

    @JsonCreator
    public static RemitCode fromJson(String remit) {
        return Stream.of(RemitCode.values())
            .filter(code -> code.name().equals(remit))
            .findFirst()
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.VOICE_CODE_NOT_FOUND));
    }
}
