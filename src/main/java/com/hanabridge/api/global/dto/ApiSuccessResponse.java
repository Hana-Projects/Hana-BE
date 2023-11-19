package com.hanabridge.api.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "API 성공 응답 model")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSuccessResponse<T> {

    @Schema(description = "응답 코드")
    private int code;
    @Schema(description = "응답에 대한 메세지")
    private String message;
    @Schema(description = "응답 데이터")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private T data;

    public static <T> ApiSuccessResponse<T> success200(String message, T data) {
        return new ApiSuccessResponse<>(201, message, data);
    }
}
