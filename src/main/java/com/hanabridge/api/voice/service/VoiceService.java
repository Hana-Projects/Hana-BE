package com.hanabridge.api.voice.service;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.transaction.domain.RemitCode;
import com.hanabridge.api.voice.domain.ItemGuide;
import com.hanabridge.api.voice.domain.RemitAccountGuide;
import com.hanabridge.api.voice.domain.RemitCommonGuide;
import com.hanabridge.api.voice.domain.RemitPhoneGuide;
import com.hanabridge.api.voice.domain.VoiceCode;
import com.hanabridge.api.voice.dto.GuideRequest;
import com.hanabridge.api.voice.dto.GuideResponse;
import com.hanabridge.api.voice.dto.VoiceRequest;
import com.hanabridge.api.voice.dto.VoiceResponse;
import com.hanabridge.api.voice.repository.ItemGuideRepository;
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
    private final ItemGuideRepository itemGuideRepository;

    @Transactional
    public VoiceResponse getInitGuide(VoiceRequest request) {

        if (isRemit(request.getUserVoice())) {

            return remitCommonGuideRepository.findFirstByOrderByIdAsc()
                .map(RemitCommonGuide::toRemitVoiceResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        } else if (isItem(request.getUserVoice())) {
            return remitCommonGuideRepository.findById(3L)
                .map(RemitCommonGuide::toItemVoiceResponse)
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

        } else if (isItem(request.getVoiceCode())) {
            return itemGuideRepository.findById(request.getIndex())
                .map(ItemGuide::toGuideResponse)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND));
        }
        throw new DataNotFoundException(ErrorCode.GUIDE_NOT_FOUND);

    }

    private Boolean isRemit(String request) {
        return request.contains("송금") || request.contains("돈") || request.contains("입금")
            || request.contains("보내") || request.contains("이체");
    }

    private Boolean isRemit(VoiceCode code) {
        return VoiceCode.REMIT == code;
    }

    private Boolean isItem(String request) {
        return request.contains("상품") || request.contains("혜택") || request.contains("할인")
            || request.contains("생활비") || request.contains("연금") || request.contains("증권")
            || request.contains("집") || request.contains("가입") || request.contains("아이템")
            || request.contains("해택") || request.contains("통장") || request.contains("주거래")
            || request.contains("만들") || request.contains("카드") || request.contains("적금")
            || request.contains("예금") || request.contains("계좌") || request.contains("금리")
            || request.contains("펀드") || request.contains("저축") || request.contains("하나") ||
            request.contains("추천") || request.contains("기름값") || request.contains("주유비")
            || request.contains("장보기") || request.contains("마트") || request.contains("홈플러스")
            || request.contains("학원") || request.contains("자격증") || request.contains("어학")
            || request.contains("시험") || request.contains("통신비") || request.contains("요금")
            || request.contains("핸드폰") || request.contains("휴대폰") || request.contains("스마트폰")
            || request.contains("요금") || request.contains("숙박") || request.contains("여행")
            || request.contains("버스비") || request.contains("지하철비") || request.contains("택시비")
            || request.contains("병원") || request.contains("건강") || request.contains("의료");
    }

    private Boolean isItem(VoiceCode code) {
        return VoiceCode.ITEM == code;
    }
}
