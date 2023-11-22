package com.hanabridge.api.security.service;

import com.hanabridge.api.customer.domain.Customer;
import com.hanabridge.api.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {

        return customerRepository.findByUsername(username)
                .map(Customer::toUserDetails)
                .orElseThrow(() -> new AuthenticationServiceException("해당 아이디가 존재하지 않습니다."));

    }

}
