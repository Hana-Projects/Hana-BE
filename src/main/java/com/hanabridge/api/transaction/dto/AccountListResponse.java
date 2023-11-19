package com.hanabridge.api.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "계좌 목록 조회 model")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountListResponse {

    @Schema(description = "총 계좌 갯수")
    private int total;
    @Schema(description = "소유한 계좌 각각의 정보(계좌 종류, 계좌번호, 잔액) 리스트")
    private List<AccountResponse> accountResponses;

    public static AccountListResponse fromList(int total, List<AccountResponse> list) {
        return AccountListResponse.builder()
            .total(total)
            .accountResponses(list)
            .build();
    }
}
