package com.hanabridge.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.dto.ApiErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

@NoArgsConstructor
@Slf4j
public class JwtAuthenticationExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException");
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            log.error("UnsupportedJwtException");
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (SignatureException e) {
            log.error("SignatureException");
            setErrorResponse(response, ErrorCode.INVALID_SIGNATURE);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(objectMapper.writeValueAsString(
                ApiErrorResponse.builder()
                    .code(errorCode.getHttpStatus().value())
                    .message(errorCode.getMessage()).build()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
