package com.hanabridge.api.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "캘린더 청약 있는 날짜 표시 응답 Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDateResponse {

    private Integer day;
    private Boolean check;
}
