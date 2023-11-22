package com.hanabridge.api.transaction.domain;

import com.hanabridge.api.customer.domain.Customer;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.InValidException;
import com.hanabridge.api.transaction.dto.AccountResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "BANK_CODE")
    @Enumerated(EnumType.STRING)
    private BankCode bankCode;

    @Column(name = "BALANCE")
    private Long balance;

    @Column(name = "ACCOUNT_CODE")
    @Enumerated(EnumType.STRING)
    private AccountCode accountCode;

    public AccountResponse toAccountResponse() {
        return AccountResponse.builder()
            .accountId(id)
            .accountCode(accountCode)
            .accountNumber(accountNumber)
            .balance(balance)
            .build();
    }

    public void withdraw(Long amount) {
        if (balance < amount) {
            throw new InValidException(ErrorCode.BALANCE_NOT_ENOUGH);
        }
        balance -= amount;
    }

    public void deposit(Long amount) {
        balance += amount;
    }
}
