package com.hanabridge.api.transaction.controller;

import com.hanabridge.api.global.dto.ApiSuccessResponse;
import com.hanabridge.api.global.utils.TextMessageSender;
import com.hanabridge.api.transaction.domain.ItemCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "문자 메세지 API", description = "문자 메세지 전송 관련 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final TextMessageSender textMessageSender;

    @Operation(summary = "상품 안내 문자 메세지", description = "선택한 상품에 대한 자세한 설명을 문자로 받아보는 api이다.")
    @Parameter(name = "itemCode",description = "상품 코드 값",example = ""
        + "    \nHANA_ACCOUNT = 하나 주거래 통장 \n"
        + "    \nWONDER_LIVING_CARD = 원더 LIVING 카드\n"
        + "    \nWONDER_FREE = 원더카드 FREE\n"
        + "    \nCHALLENGE_365_SAVINGS = 도전 365 적금\n"
        + "    \nSTOCK_SAVINGS_ACCOUNT = 증권 저축 계좌\n"
        + "    \nINTEREST_RATE_FREE_FUND = 금리 걱정 없는 펀드\n"
        + "    \nPENSION_HANA_SAVINGS = 연금하나 월복리 적금 \n"
        + "    \nPENSION_HANA_ACCOUNT = 연금하나 통장\n"
        + "    \nPENSION_SAVINGS_ACCOUNT = 연금저축계좌\n"
        + "    \nHOUSE_APPLICATION = 주택청약종합저축\n"
        + "    \nDOUBLE_UP_SAVINGS = 내집마련 더블업적금\n"
        + "    \nHOUSE_STABLE_LOAN = 주거안정 주택구입 자금 대출")
    @PostMapping("/item/message")
    public ResponseEntity<ApiSuccessResponse<Void>> sendItemMessage(@RequestParam("itemCode")ItemCode itemCode){
        return ResponseEntity.ok(ApiSuccessResponse.success200("문자 메세지 전송 성공"));
    }

}
