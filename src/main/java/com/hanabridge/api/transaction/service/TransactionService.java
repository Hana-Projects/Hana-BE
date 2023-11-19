package com.hanabridge.api.transaction.service;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.transaction.domain.Account;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import com.hanabridge.api.transaction.dto.AccountResponse;
import com.hanabridge.api.transaction.dto.NumberBookListResponse;
import com.hanabridge.api.transaction.dto.NumberRemitRequest;
import com.hanabridge.api.transaction.dto.RemitRequest;
import com.hanabridge.api.transaction.repository.AccountRepository;
import com.hanabridge.api.transaction.repository.NumberBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final AccountRepository accountRepository;
    private final NumberBookRepository numberBookRepository;

    @Transactional
    public AccountListResponse getAccountList(Long id) {

        List<AccountResponse> accountList =
            accountRepository.findByCustomer_Id(id)
                .stream()
                .map(Account::toAccountResponse)
                .toList();

        return AccountListResponse.fromList(accountList.size(), accountList);
    }

    @Transactional
    public void remit(RemitRequest request) {
        //출금
        accountRepository.findById(request.getAccountId())
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND))
            .withdraw(request.getAmount());
        //입금
        accountRepository.findByAccountNumber(request.getAccountNumber())
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND))
            .deposit(request.getAmount());

    }

    @Transactional
    public List<NumberBookListResponse> getNumberList(Long id) {

        return numberBookRepository.getNumberList(id);

    }

    @Transactional
    public void numberRemit(NumberRemitRequest request) {
        //출금
        accountRepository.findById(request.getAccountId())
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND))
            .withdraw(request.getAmount());
        //입금
        accountRepository.findByAccountNumber(request.getToAccountNumber())
            .orElseThrow(() -> new DataNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND))
            .deposit(request.getAmount());

    }

}
