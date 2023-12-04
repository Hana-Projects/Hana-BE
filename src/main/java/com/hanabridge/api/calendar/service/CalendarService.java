package com.hanabridge.api.calendar.service;

import com.hanabridge.api.calendar.dto.APTDataResponse;
import com.hanabridge.api.calendar.dto.CalendarDateListResponse;
import com.hanabridge.api.calendar.dto.CalendarDateRequest;
import com.hanabridge.api.calendar.dto.CalendarDateResponse;
import com.hanabridge.api.calendar.dto.CalendarRequest;
import com.hanabridge.api.calendar.dto.CalendarResponse;
import com.hanabridge.api.calendar.dto.OpenApiAPTResponse;
import com.hanabridge.api.calendar.dto.OpenApiUrbtyOfctlResponse;
import com.hanabridge.api.calendar.dto.UrbtyOfctlDataResponse;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.global.exception.OpenApiException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class CalendarService {

    @Value("${calendar.serviceKey}")
    private String serviceKey;
    private final String APTUrl = "https://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancDetail";

    private final String UrbtyOfctlUrl = "https://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getUrbtyOfctlLttotPblancDetail";

    public List<CalendarResponse> callSubscriptionInfo(CalendarRequest request) {

        List<CalendarResponse> aptResponse = null;
        List<CalendarResponse> urbtyOfctlResponse = null;

//        urbtyOfctlResponse = callUrbtyOfctlInfo().stream()
//            .filter((urbtyOfctlDataResponse) ->
//                LocalDate.parse(urbtyOfctlDataResponse.getReceiptEndDate())
//                    .isAfter(LocalDate.of(request.getYear(), request.getMonth(), request.getDay())))
//            .limit(3)
//            .map(UrbtyOfctlDataResponse::toCalendarResponse)
//            .toList();

        // if (urbtyOfctlResponse.size() < 3) {
        List<APTDataResponse> response = callAPTInfo();
        Collections.shuffle(response);

        aptResponse = response.stream()
            .filter((aptDataResponse) -> (LocalDate.parse(aptDataResponse.getReceiptEndDate())
                .isAfter(
                    LocalDate.of(request.getYear(), request.getMonth(), request.getDay())) || LocalDate.parse(aptDataResponse.getReceiptEndDate())
                .isEqual(
                    LocalDate.of(request.getYear(), request.getMonth(), request.getDay())))
                && (LocalDate.parse(aptDataResponse.getReceiptBeginDate())
                .isBefore(LocalDate.of(request.getYear(), request.getMonth(), request.getDay())) || LocalDate.parse(aptDataResponse.getReceiptBeginDate())
                .isEqual(
                    LocalDate.of(request.getYear(), request.getMonth(), request.getDay()))))
            //.limit(3 - urbtyOfctlResponse.size())
            .limit(3)
            .map(APTDataResponse::toCalendarResponse)
            .toList();

        //return Stream.concat(urbtyOfctlResponse.stream(), aptResponse.stream()).toList();
        return aptResponse;
        // }
//        List<UrbtyOfctlDataResponse> response = callUrbtyOfctlInfo();
//        Collections.shuffle(response);
//
//        aptResponse = response.stream()
//            .filter((aptDataResponse) -> LocalDate.parse(aptDataResponse.getReceiptEndDate())
//                .isAfter(
//                    LocalDate.of(request.getYear(), request.getMonth(), request.getDay()))
//                && LocalDate.parse(aptDataResponse.getReceiptBeginDate())
//                .isBefore(LocalDate.of(request.getYear(), request.getMonth(), request.getDay())))
//            //.limit(3 - urbtyOfctlResponse.size())
//            .limit(3)
//            .map(UrbtyOfctlDataResponse::toCalendarResponse)
//            .toList();

//        return aptResponse;
    }

    public CalendarDateListResponse checkSubscriptionDate(CalendarDateRequest request) {

        int maxDays = YearMonth.of(request.getYear(), request.getMonth()).lengthOfMonth();
        List<CalendarDateResponse> calendarDateResponses = new ArrayList<>();
        List<APTDataResponse> response = callAPTInfo();

        for (int i = 1; i <= maxDays; i++) {
            CalendarDateResponse calendarData = new CalendarDateResponse(i, false);
            boolean check = response.stream()
                .anyMatch(
                    (aptDataResponse -> checkBetweenDate(aptDataResponse, request, calendarData)));
            if (check == true) {
                calendarData.setCheck(true);
            }

            calendarDateResponses.add(calendarData);
        }
        return new CalendarDateListResponse(request.getYear(), request.getMonth(),
            calendarDateResponses);
    }

    private List<APTDataResponse> callAPTInfo() {

        try {

            UriComponents url = getUriComponents(APTUrl);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<OpenApiAPTResponse> entity = restTemplate.getForEntity(
                url.toUriString(), OpenApiAPTResponse.class);

            return entity.getBody().getData();

        } catch (RestClientException e) {
            throw new OpenApiException(ErrorCode.OPENAPI_ERROR);
        } catch (NullPointerException e) {
            throw new DataNotFoundException(ErrorCode.HOME_INFO_NOT_FOUND);
        }
    }

    private List<UrbtyOfctlDataResponse> callUrbtyOfctlInfo() {

        try {

            UriComponents url = getUriComponents(UrbtyOfctlUrl);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<OpenApiUrbtyOfctlResponse> entity = restTemplate.getForEntity(
                url.toUriString(), OpenApiUrbtyOfctlResponse.class);

            return entity.getBody().getData();

        } catch (RestClientException e) {
            throw new OpenApiException(ErrorCode.OPENAPI_ERROR);
        } catch (NullPointerException e) {
            throw new DataNotFoundException(ErrorCode.HOME_INFO_NOT_FOUND);
        }
    }

    private UriComponents getUriComponents(String url) {

        return UriComponentsBuilder
            .fromHttpUrl(url)
            .queryParam("page", 1)
            .queryParam("perPage", 1780)
            .queryParam("cond[SUBSCRPT_AREA_CODE_NM::EQ]", "서울")
            .queryParam("serviceKey", serviceKey)
            .build();
    }

    private Boolean checkBetweenDate(APTDataResponse aptDataResponse,
        CalendarDateRequest request,
        CalendarDateResponse calendarData) {
        return (LocalDate.parse(aptDataResponse.getReceiptBeginDate())
            .isBefore(LocalDate.of(request.getYear(), request.getMonth(), calendarData.getDay())) ||
            LocalDate.parse(aptDataResponse.getReceiptBeginDate())
            .isEqual(LocalDate.of(request.getYear(), request.getMonth(), calendarData.getDay())))
            && (LocalDate.parse(aptDataResponse.getReceiptEndDate())
                .isAfter(LocalDate.of(request.getYear(), request.getMonth(), calendarData.getDay())) ||
            LocalDate.parse(aptDataResponse.getReceiptEndDate())
                .isEqual(LocalDate.of(request.getYear(), request.getMonth(), calendarData.getDay())));

    }
}
