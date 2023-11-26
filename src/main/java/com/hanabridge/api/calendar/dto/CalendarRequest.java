package com.hanabridge.api.calendar.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "캘린더 청약 정보 요청 model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarRequest {

    @Parameter(name = "year", description = "조회하고 싶은 년도", required = true)
    private Integer year;

    @Parameter(name = "month", description = "조회하고 싶은 월", required = true)
    private Integer month;

    @Parameter(name = "day", description = "조회하고 싶은 일", required = true)
    private Integer day;
}
