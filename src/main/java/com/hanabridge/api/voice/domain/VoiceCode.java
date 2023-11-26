package com.hanabridge.api.voice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum VoiceCode {
    REMIT, ITEM;

    @JsonCreator
    public static VoiceCode fromJson(String voice) {
        return Stream.of(VoiceCode.values())
            .filter(code -> code.name().equals(voice))
            .findFirst()
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.VOICE_CODE_NOT_FOUND));
    }
}
