package com.hanabridge.api.customer.dto;

import com.hanabridge.api.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRegisterRequest {
    String username;
    String password;

    public Customer toEntity() {
        return Customer.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
