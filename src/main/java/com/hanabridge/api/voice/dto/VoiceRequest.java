package com.hanabridge.api.voice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "유저 음성", description = "메인 화면에서 유저가 입력한 음성")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceRequest {

    @Schema(description = "메인 화면에서 유저가 입력한 음성")
    private String userVoice;
}
