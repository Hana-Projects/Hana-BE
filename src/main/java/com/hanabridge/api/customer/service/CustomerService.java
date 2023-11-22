package com.hanabridge.api.customer.service;

import com.hanabridge.api.customer.dto.CustomerRegisterRequest;
import com.hanabridge.api.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(CustomerRegisterRequest request) {
        String encodePassword = passwordEncoder.encode(request.getPassword());
        customerRepository.save(request.toEntity(encodePassword));
    }
}
