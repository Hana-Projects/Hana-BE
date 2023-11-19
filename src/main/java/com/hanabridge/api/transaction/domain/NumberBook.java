package com.hanabridge.api.transaction.domain;

import com.hanabridge.api.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NUMBER_BOOK")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NumberBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FROM_CUSTOMER_ID", nullable = false)
    private Customer fromCustomer;

    @ManyToOne
    @JoinColumn(name = "TO_CUSTOMER_ID", nullable = false)
    private Customer toCustomer;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "NUMBER_NAME", nullable = false)
    private String numberName;
}
