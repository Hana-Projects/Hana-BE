package com.hanabridge.api.security.filter;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.global.exception.DataNotFoundException;
import com.hanabridge.api.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails findUserDetails = customUserDetailsService
                .loadUserByUsername(authentication.getPrincipal().toString());
        if (!Objects.equals(authentication.getCredentials().toString(), findUserDetails.getPassword())) {
            throw new DataNotFoundException(ErrorCode.PASSWORD_NOT_MATCH);
        } else {
            return new UsernamePasswordAuthenticationToken(findUserDetails, null, null);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
