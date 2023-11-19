package com.hanabridge.api.transaction.domain;

import com.hanabridge.api.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PHONE_NUMBER_ACCOUNT")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PhoneNumberAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;
}
