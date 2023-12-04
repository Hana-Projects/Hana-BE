package com.hanabridge.api.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemCode {

    HANA_ACCOUNT("하나 주거래 통장"),
    WONDER_LIVING_CARD("원더 LIVING 카드"),
    WONDER_FREE("원더카드 FREE"),
    CHALLENGE_365_SAVINGS("도전 365 적금"),
    STOCK_SAVINGS_ACCOUNT("증권 저축 계좌"),
    INTEREST_RATE_FREE_FUND("금리 걱정 없는 펀드"),
    PENSION_HANA_SAVINGS("연금하나 월복리 적금"),
    PENSION_HANA_ACCOUNT("연금하나 통장"),
    PENSION_SAVINGS_ACCOUNT("연금저축계좌"),
    HOUSE_APPLICATION("주택청약종합저축"),
    DOUBLE_UP_SAVINGS("내집마련 더블업적금"),
    HOUSE_STABLE_LOAN("주거안정 주택구입 자금 대출");
    private String name;
}
