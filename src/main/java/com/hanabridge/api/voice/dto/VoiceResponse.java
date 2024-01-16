package com.hanabridge.api.voice.dto;

import com.hanabridge.api.voice.domain.VoiceCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "메인 화면 안내 model", description = "메인 화면에서 유저가 입력한 음성에 따른 안내 응답 model")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoiceResponse {

    @Schema(description = "송금 안내인지 상품 안내인지 판단해 줄 수 있는 값. REMIT 또는 ITEM")
    private VoiceCode voiceCode;
    @Schema(description = "안내 내용")
    private String guide;
    @Schema(description = "안내 화면의 index 값. 메인 화면임으로 0이다.")
    private Long index;
}
