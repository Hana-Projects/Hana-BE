package com.hanabridge.api.global.utils;


import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextMessageSender {

    private final DefaultMessageService messageService;

    @Value("${coolsms.from.number}")
    private String fromNumber;

    @Value("${coolsms.to.number}")
    private String toNumber;

    public void sendTextMessage() {
        Message message = new Message();
        message.setFrom(fromNumber);
        message.setTo(toNumber);
        message.setText("Test 발송2 11/20 11:00\n"
            + "입금 100,000원\n"
            + "이상민 -> 내 계좌(213-******-*****)\n"
            + "잔액 100,000원");

        messageService.sendOne(
            new SingleMessageSendingRequest(message));

    }
}
