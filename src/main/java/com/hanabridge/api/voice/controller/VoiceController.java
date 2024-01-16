package com.hanabridge.api.voice.controller;

import com.hanabridge.api.global.dto.ApiSuccessResponse;
import com.hanabridge.api.voice.dto.GuideRequest;
import com.hanabridge.api.voice.dto.GuideResponse;
import com.hanabridge.api.voice.dto.VoiceRequest;
import com.hanabridge.api.voice.dto.VoiceResponse;
import com.hanabridge.api.voice.service.VoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "음성 안내 API", description = "사용자가 음성을 입력하고 그에 해당하는 서비스 이용 방법을 안내해 주는 API이다.")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class VoiceController {

    private final VoiceService voiceService;

    @Operation(summary = "안내 시작 API", description =
        "사용자가 입력한 음성이 송금에 관한 것인지 상품에 관한 것인지 판단하고 안내를 시작해주는 API이다. "
            + "해당 API는 음성을 입력할 때만 사용되는 API이다. 추후 안내는 /api/voice/guide를 사용해야 한다.")
    @PostMapping("/voice")
    public ResponseEntity<ApiSuccessResponse<VoiceResponse>> getInitGuide(
        @RequestBody VoiceRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("안내 시작", voiceService.getInitGuide(request)));
    }

    @Operation(summary = "안내 상세 API", description = "송금 또는 상품 프로세스를 안내해주는 API이다. "
        + "상품 프로세스와 계좌 프로세스 중 보낼 방법 화면까지는 remitCode는 NOTDECIDE이다.")
    @PostMapping("/voice/guide")
    public ResponseEntity<ApiSuccessResponse<GuideResponse>> getGuide(
        @RequestBody GuideRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiSuccessResponse.success200("안내 호출 성공", voiceService.getGuide(request)));
    }
}
