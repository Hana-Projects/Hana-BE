package com.hanabridge.api.transaction.dto;

import com.hanabridge.api.transaction.domain.BankCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "연락처 목록 조회 model")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NumberBookListResponse {

    @Schema(description = "연락처에 저장된 고객 id(primary key) 값")
    private Long toCustomerId;
    @Schema(description = "연락처에 저장된 고객 전화번호와 연결된 계좌 번호")
    private String accountNumber;
    @Schema(description = "연락처에 저장된 고객 전화번호와 연결된 계좌의 은행")
    private BankCode bankCode;
    @Schema(description = "연락처에 저장된 고객 전화번호")
    private String phoneNumber;
    @Schema(description = "연락처에 저장된 고객 이름")
    private String numberName;

}
