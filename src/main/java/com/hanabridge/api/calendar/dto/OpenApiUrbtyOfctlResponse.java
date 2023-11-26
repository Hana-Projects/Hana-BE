package com.hanabridge.api.calendar.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiUrbtyOfctlResponse {

    private Long page;
    private Long perPage;
    private Long totalCount;
    private Long currentCount;
    private Long matchCount;
    private List<UrbtyOfctlDataResponse> data;
}
