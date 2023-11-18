package com.hanabridge.api.customer.domain;

import com.hanabridge.api.security.dto.CustomUserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USER_NAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public CustomUserDetails toUserDetails() {
        return CustomUserDetails.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

}
