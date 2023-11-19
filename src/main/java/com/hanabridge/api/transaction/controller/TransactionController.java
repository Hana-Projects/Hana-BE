package com.hanabridge.api.transaction.controller;

import com.hanabridge.api.global.dto.ApiSuccessResponse;
import com.hanabridge.api.security.dto.CustomUserDetails;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import com.hanabridge.api.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "송금 API", description = "송금 관련 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "계좌 목록 조회 API", description = "고객의 계좌를 조회하는 API이다.")
    @GetMapping("/accounts")
    public ResponseEntity<ApiSuccessResponse<AccountListResponse>> getAccountList(
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(
            ApiSuccessResponse.success200("계좌 목록 조회 성공",
                transactionService.getAccountList(userDetails.getId())));
    }
}
