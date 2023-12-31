package com.hanabridge.api.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ProviderManager providerManager;

    public JwtAuthenticationFilter(AuthenticationProvider authenticationProvider) {
        this.providerManager = new ProviderManager(authenticationProvider);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        log.info("customAuthenticationFilter 실행됨!!!!!!!!!!");
        Authentication authenticated = providerManager.authenticate(getAuthentication());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticated);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);

    }

    private Authentication getAuthentication() {
        return new UsernamePasswordAuthenticationToken("sangmin", "1234");
    }
}
