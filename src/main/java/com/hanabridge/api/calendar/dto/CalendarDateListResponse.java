package com.hanabridge.api.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "캘린더 청약 있는 날짜 표시 리스트 응답 Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDateListResponse {

    private Integer year;

    private Integer month;

    private List<CalendarDateResponse> calendarDateResponses;
}
