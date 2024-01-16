package com.hanabridge.api.calendar.controller;

import com.hanabridge.api.calendar.dto.CalendarDateListResponse;
import com.hanabridge.api.calendar.dto.CalendarDateRequest;
import com.hanabridge.api.calendar.dto.CalendarRequest;
import com.hanabridge.api.calendar.dto.CalendarResponse;
import com.hanabridge.api.calendar.service.CalendarService;
import com.hanabridge.api.global.dto.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "캘린더 API", description = "캘린더 관련 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @Operation(summary = "청약 목록 조회 API", description = "해당 날짜의 청약 정보를 조회하는 API이다.")
    @GetMapping("/calendar")
    public ResponseEntity<ApiSuccessResponse<List<CalendarResponse>>> getSubscriptionInfo(
        @ParameterObject @ModelAttribute CalendarRequest request) {

        List<CalendarResponse> response = calendarService.callSubscriptionInfo(request);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("청약 정보 조회 성공", response));
    }

    @Operation(summary = "해당 월의 청약 여부 조회 API", description = "해당 년.월에 청약이 존재하는 날짜를 조회하는 API이다.")
    @GetMapping("/calendar/date")
    public ResponseEntity<ApiSuccessResponse<CalendarDateListResponse>> getSubscriptionDateInfo(
        @ParameterObject @ModelAttribute CalendarDateRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("해당 월의 청약 여부 조회 성공",
                calendarService.checkSubscriptionDate(request)));

    }
}
