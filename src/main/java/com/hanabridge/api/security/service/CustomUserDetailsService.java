package com.hanabridge.api.security.service;

import com.hanabridge.api.customer.domain.Customer;
import com.hanabridge.api.customer.repository.CustomerRepository;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return customerRepository.findByUsername(username)
                .map(Customer::toUserDetails)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.USERNAME_NOT_FOUND));

    }

}
