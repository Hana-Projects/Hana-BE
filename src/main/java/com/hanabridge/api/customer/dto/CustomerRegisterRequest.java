package com.hanabridge.api.customer.dto;

import com.hanabridge.api.customer.domain.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "회원 가입 요청 model")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterRequest {
    @Schema(name = "유저 id", example = "haha123")
    String username;
    @Schema(name = "패스워드", example = "123")
    String password;

    public Customer toEntity() {
        return Customer.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
