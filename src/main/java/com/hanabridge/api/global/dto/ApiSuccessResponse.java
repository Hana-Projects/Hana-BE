package com.hanabridge.api.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiSuccessResponse<T> {

    private int code;
    private String message;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private T data;
}
