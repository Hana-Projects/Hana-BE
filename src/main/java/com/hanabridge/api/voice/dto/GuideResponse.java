package com.hanabridge.api.voice.dto;

import com.hanabridge.api.voice.domain.VoiceCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "안내 응답 model", description = "/api/voice 호출 이후 /api/voice/guide에서 사용하는 model")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuideResponse {

    @Schema(description = "송금 안내인지 상품 안내인지 판단해 줄 수 있는 값. REMIT 또는 ITEM")
    private VoiceCode voiceCode;
    @Schema(description = "안내 내용")
    private String guide;
    @Schema(description = "해당 화면의 index 값")
    private Long index;
}
