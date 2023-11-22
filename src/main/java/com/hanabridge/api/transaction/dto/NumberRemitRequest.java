package com.hanabridge.api.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "연락처 송금 요청 model")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NumberRemitRequest {

    @Schema(description = "입금(돈을 받는) 고객 계좌 번호")
    private String toAccountNumber;
    @Schema(description = "출금(돈을 보내는) 계좌의 id(primary key)값")
    private Long accountId;
    @Schema(description = "송금하는 금액")
    private Long amount;
}
