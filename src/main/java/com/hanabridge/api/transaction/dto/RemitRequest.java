package com.hanabridge.api.transaction.dto;

import com.hanabridge.api.transaction.domain.BankCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "계좌 송금 요청 model")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemitRequest {

    @Schema(description = "돈이 나가는 자신의 계좌 id값")
    private Long accountId;
    @Schema(description = "돈을 보낼 계좌 번호")
    private String accountNumber;
    @Schema(description = "돈을 보낼 계좌의 은행 HANA=하나,WOORI=우리,KB=국민,SHINHAN=신한,NH=농협,SUHYUP=수협,SC=제일", examples = {
        "HANA", "WOORI", "KB", "SHINHAN", "NH", "SUHYUP", "SC"})
    private BankCode bankCode;
    @Schema(description = "송금 금액")
    private Long amount;
}
