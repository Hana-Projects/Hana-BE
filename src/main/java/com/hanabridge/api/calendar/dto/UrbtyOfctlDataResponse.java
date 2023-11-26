package com.hanabridge.api.calendar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrbtyOfctlDataResponse {

    @JsonProperty("HOUSE_MANAGE_NO")
    private Integer houseManageNo;
    @JsonProperty("PBLANC_NO")
    private Integer announcementNo;
    @JsonProperty("HOUSE_NM")
    private String houseName;
    @JsonProperty("SUBSCRPT_RCEPT_BGNDE")
    private String receiptBeginDate;
    @JsonProperty("SUBSCRPT_RCEPT_ENDDE")
    private String receiptEndDate;
    @JsonProperty("HSSPLY_ADRES")
    private String address;

    public CalendarResponse toCalendarResponse() {
        return CalendarResponse.builder()
            .houseName(this.houseName)
            .receiptEndDate(this.receiptEndDate)
            .address(this.address)
            .build();
    }
}
