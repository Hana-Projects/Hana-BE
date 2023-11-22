package com.hanabridge.api.transaction.dto;

import com.hanabridge.api.transaction.domain.AccountCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "계좌 정보 model")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @Schema(description = "계좌 ID(primary key) 값")
    private Long accountId;
    @Schema(description = "계좌 종류 타입", examples = {"DEPOSITANDWITHDRAW=입출금", "SAVING=예금", "FUND=펀드"})
    private AccountCode accountCode;
    @Schema(description = "계좌 번호", example = "000-000000-00000")
    private String accountNumber;
    @Schema(description = "계좌 잔액", example = "1,000,000")
    private Long balance;

}
