package com.hanabridge.api.voice.dto;

import com.hanabridge.api.transaction.domain.RemitCode;
import com.hanabridge.api.voice.domain.VoiceCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "안내 요청 model", description = "/api/voice 호출 이후 /api/voice/guide에서 사용하는 model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideRequest {

    @Schema(description = "송금 안내인지 상품 안내인지 판단해 줄 수 있는 값. REMIT 또는 ITEM")
    @NotNull(message = "voiceCode는 필수 값입니다.")
    VoiceCode voiceCode;
    @Schema(description = "상품과 송금 중 송금 방법 선택 화면까지는 NOTDECIDE 이다. 연락처 송금은 PHONE, 계좌 송금은 ACCOUNT.")
    @NotNull(message = "remitCode는 필수 값입니다.")
    RemitCode remitCode;
    @Schema(description =
        "index 1 부터 시작한다. 예를 들어 돈 보낼 방법을 선택 하는 화면의 안내 index는 1이다. 송금 방법을 선택한 이후에도 index는 1씩 증가한다. "
            + "예를 들어 계좌 목록 조회는 index 2, send 2-1 화면 index 3, send 1-1 화면 index 3")
    @NotNull(message = "index는 필수 값입니다.")
    Long index;
}
