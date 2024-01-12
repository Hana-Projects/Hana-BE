package com.hanabridge.api.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanabridge.api.global.dto.ApiSuccessResponse;
import com.hanabridge.api.security.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        log.info("login success by AuthenticationSuccessHandler");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(objectMapper.writeValueAsString(
                ApiSuccessResponse.success200("로그인 성공", jwtService.create(authentication))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
