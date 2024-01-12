package com.hanabridge.api.security.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.prefix}")
    private String prefix;
    @Value("${jwt.expiredMinute}")
    private int expiredMinute;
}
