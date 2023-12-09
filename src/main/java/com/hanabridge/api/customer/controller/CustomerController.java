package com.hanabridge.api.customer.controller;


import com.hanabridge.api.customer.dto.CustomerRegisterRequest;
import com.hanabridge.api.customer.service.CustomerService;
import com.hanabridge.api.global.dto.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "유저 API", description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

//    @Operation(summary = "회원 가입 요청", description = "회원 가입 요청 API")
//    @ApiResponse(
//            responseCode = "201",
//            description = "회원 가입 요청 성공",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schemaProperties = {
//                    @SchemaProperty(name = "code", schema = @Schema(type = "Integer", example = "201")),
//                    @SchemaProperty(name = "message", schema = @Schema(type = "String", example = "회원 가입 성공"))
//            })
//    )
//    @PostMapping("/register")
//    public ResponseEntity<ApiSuccessResponse<Void>> register(@RequestBody CustomerRegisterRequest request) {
//        customerService.register(request);
//
//        ApiSuccessResponse<Void> response = new ApiSuccessResponse<>(201, "회원 가입 성공", null);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<ApiSuccessResponse<Void>> login(){
//        return ResponseEntity.ok(ApiSuccessResponse.success200("로그인 성공"));
//    }
}
