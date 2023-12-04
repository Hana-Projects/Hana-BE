package com.hanabridge.api.calendar.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "캘린더 청약 있는 날짜 표시 요청 Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDateRequest {

    @Parameter(name = "year", description = "조회하고 싶은 년도", required = true)
    private Integer year;

    @Parameter(name = "month", description = "조회하고 싶은 월", required = true)
    private Integer month;

}
