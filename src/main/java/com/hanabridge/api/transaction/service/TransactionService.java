package com.hanabridge.api.transaction.service;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.transaction.domain.Account;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import com.hanabridge.api.transaction.dto.AccountResponse;
import com.hanabridge.api.transaction.dto.RemitRequest;
import com.hanabridge.api.transaction.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

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
}
