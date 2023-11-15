package com.hanabridge.api.global.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ApiErrorResponse {

    private int code;
    private String message;
}
