package com.hanabridge.api.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "캘린더 청약 정보 응답 model")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarResponse {

    @Schema(description = "청약 이름")
    private String houseName;
    @Schema(description = "청약 만료 기간")
    private String receiptEndDate;
    @Schema(description = "주소")
    private String address;
}
