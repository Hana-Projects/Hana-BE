package com.hanabridge.api.transaction.repository;

import com.hanabridge.api.transaction.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer_Id(Long id);
}
