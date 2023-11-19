package com.hanabridge.api.transaction.service;

import com.hanabridge.api.transaction.domain.Account;
import com.hanabridge.api.transaction.dto.AccountListResponse;
import com.hanabridge.api.transaction.dto.AccountResponse;
import com.hanabridge.api.transaction.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    public AccountListResponse getAccountList(Long id) {

        List<AccountResponse> accountList =
            accountRepository.findByCustomer_Id(id)
                .stream()
                .map(Account::toAccountResponse)
                .toList();

        return AccountListResponse.fromList(accountList.size(), accountList);
    }
}
