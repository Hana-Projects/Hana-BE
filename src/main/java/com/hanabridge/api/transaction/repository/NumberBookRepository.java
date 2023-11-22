package com.hanabridge.api.transaction.repository;

import com.hanabridge.api.transaction.domain.NumberBook;
import com.hanabridge.api.transaction.dto.NumberBookListResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NumberBookRepository extends JpaRepository<NumberBook, Long> {


    @Query("Select distinct new com.hanabridge.api.transaction.dto.NumberBookListResponse"
        + "(n.toCustomer.id,a.accountNumber,a.bankCode,n.phoneNumber,n.numberName) "
        + "From NumberBook n "
        + "Inner Join n.toCustomer c "
        + "left Join PhoneNumberAccount p On p.customer.id = c.id "
        + "Left Join Account a On a.id = p.account.id "
        + "Where n.fromCustomer.id = :id")
    List<NumberBookListResponse> getNumberList(@Param("id") Long id);
}
