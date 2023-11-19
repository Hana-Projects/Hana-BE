package com.hanabridge.api.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSuccessResponse<T> {


    private int code;
    private String message;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private T data;

    public static ApiSuccessResponse<Void> success200(String message) {
        return new ApiSuccessResponse<>(200, message, null);
    }

    public static <T> ApiSuccessResponse<T> success200(String message, T data) {
        return new ApiSuccessResponse<>(200, message, data);
    }
}
