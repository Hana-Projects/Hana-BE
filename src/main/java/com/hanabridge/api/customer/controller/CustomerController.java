package com.hanabridge.api.customer.controller;


import com.hanabridge.api.customer.dto.CustomerRegisterRequest;
import com.hanabridge.api.global.dto.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API", description = "유저 관련 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CustomerController {


//    @PostMapping("/register")
//    public ResponseEntity<ApiSuccessResponse<Void>> register(@RequestBody CustomerRegisterRequest request){
//
//    }
}
