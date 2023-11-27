package com.hanabridge.api.voice.service;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.transaction.domain.RemitCode;
import com.hanabridge.api.voice.domain.RemitAccountGuide;
import com.hanabridge.api.voice.domain.RemitCommonGuide;
import com.hanabridge.api.voice.domain.RemitPhoneGuide;
import com.hanabridge.api.voice.domain.VoiceCode;
import com.hanabridge.api.voice.dto.GuideRequest;
import com.hanabridge.api.voice.dto.GuideResponse;
import com.hanabridge.api.voice.dto.VoiceRequest;
import com.hanabridge.api.voice.dto.VoiceResponse;
import com.hanabridge.api.voice.repository.RemitAccountGuideRepository;
import com.hanabridge.api.voice.repository.RemitCommonGuideRepository;
import com.hanabridge.api.voice.repository.RemitPhoneGuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoiceService {

    private final RemitCommonGuideRepository remitCommonGuideRepository;
    private final RemitAccountGuideRepository remitAccountGuideRepository;
    private final RemitPhoneGuideRepository remitPhoneGuideRepository;

    @Transactional
    public VoiceResponse getInitGuide(VoiceRequest request) {

        if (isRemit(request.getUserVoice())) {

            return remitCommonGuideRepository.findFirstByOrderByIdAsc()
                .map(RemitCommonGuide::toVoiceResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        }
        throw new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND);
    }
    @Transactional
    public GuideResponse getGuide(GuideRequest request) {

        if (isRemit(request.getVoiceCode()) && request.getRemitCode() == RemitCode.NOTDECIDE) {

            return remitCommonGuideRepository.findById(request.getIndex() + 1)
                .map(RemitCommonGuide::toGuideResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        } else if (isRemit(request.getVoiceCode()) && request.getRemitCode() == RemitCode.ACCOUNT) {

            return remitAccountGuideRepository.findById(request.getIndex() - 1)
                .map(RemitAccountGuide::toGuideResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        } else if (isRemit(request.getVoiceCode()) && request.getRemitCode() == RemitCode.PHONE) {

            return remitPhoneGuideRepository.findById(request.getIndex() - 1)
                .map(RemitPhoneGuide::toGuideResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        }

        throw new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND);
    }

    private Boolean isRemit(String request) {
        return request.contains("송금") || request.contains("돈") || request.contains("입금")
            || request.contains("보내");
    }

    private Boolean isRemit(VoiceCode code) {
        return VoiceCode.REMIT == code;
    }
}
