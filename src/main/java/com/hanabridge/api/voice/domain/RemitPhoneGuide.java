package com.hanabridge.api.voice.domain;

import com.hanabridge.api.voice.dto.GuideResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REMIT_PHONE_GUIDE")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RemitPhoneGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GUIDE")
    private String guide;

    public GuideResponse toGuideResponse() {
        return GuideResponse.builder()
            .index(id + 1)
            .voiceCode(VoiceCode.REMIT)
            .guide(guide)
            .build();
    }
}
