package com.hanabridge.api.customer.repository;

import com.hanabridge.api.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
