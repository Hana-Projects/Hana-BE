package com.hanabridge.api.transaction.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum BankCode {


    HANA, WOORI, KB, SHINHAN, NH, SUHYUP, SC;

    @JsonCreator
    public static BankCode fromJson(String bank) {
        return Stream.of(BankCode.values())
            .filter(code -> code.name().equals(bank))
            .findFirst()
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.BANK_CODE_NOT_FOUND));
    }
}
