package com.hanabridge.api.transaction.controller;

import com.hanabridge.api.global.dto.ApiErrorResponse;
import com.hanabridge.api.global.dto.ApiSuccessResponse;
import com.hanabridge.api.security.dto.CustomUserDetails;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import com.hanabridge.api.transaction.dto.NumberBookListResponse;
import com.hanabridge.api.transaction.dto.NumberRemitRequest;
import com.hanabridge.api.transaction.dto.RemitRequest;
import com.hanabridge.api.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "계좌 송금 API", description = "고객 계좌 정보와 보낼 정보(계좌번호,은행,금액)를 입력하고 호출하는 API")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "송금 성공",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schemaProperties = {
                @SchemaProperty(name = "code", schema = @Schema(type = "Integer", example = "200")),
                @SchemaProperty(name = "message", schema = @Schema(type = "String", example = "송금 성공"))
            })),
        @ApiResponse(
            responseCode = "400",
            description = "잔액이 부족",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                implementation = ApiErrorResponse.class
            )))
    })
    @PostMapping("/remit/account")
    public ResponseEntity<ApiSuccessResponse<Void>> remit(
        @AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody RemitRequest request) {

        transactionService.remit(request);

        return ResponseEntity.status(HttpStatus.OK).body(ApiSuccessResponse.success200("송금 성공"));
    }

    @Operation(summary = "연락처 목록 조회 API", description = "고객의 연락처에 존재하는 다른 고객 정보 목록을 조회")
    @GetMapping("/numbers")
    public ResponseEntity<ApiSuccessResponse<List<NumberBookListResponse>>> getNumberList(
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("연락처 조회 성공",
                transactionService.getNumberList(userDetails.getId())));
    }

    @Operation(summary = "연락처 송금 API", description = "연락처 정보를 이용하여 송금하는 API")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "송금 성공",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schemaProperties = {
                @SchemaProperty(name = "code", schema = @Schema(type = "Integer", example = "200")),
                @SchemaProperty(name = "message", schema = @Schema(type = "String", example = "송금 성공"))
            })),
        @ApiResponse(
            responseCode = "400",
            description = "잔액이 부족",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                implementation = ApiErrorResponse.class
            )))
    })
    @PostMapping("/remit/number")
    public ResponseEntity<ApiSuccessResponse<Void>> numberRemit(
        @RequestBody NumberRemitRequest request) {

        transactionService.numberRemit(request);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("연락처로 송금 성공"));
    }
}
