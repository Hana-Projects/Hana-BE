package com.hanabridge.api.security.service;

import com.hanabridge.api.global.code.ErrorCode;
import com.hanabridge.api.security.dto.CustomUserDetails;
import com.hanabridge.api.security.dto.TokenInfo;
import com.hanabridge.api.security.utils.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public TokenInfo create(Authentication authentication) {
        return TokenInfo.builder().accessToken(createAccessToken(authentication)).build();
    }

    public String createAccessToken(Authentication authentication) {

        Date date = new Date();

        return Jwts.builder()
            .setSubject("token")
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setHeaderParam("alg", "HS256")
            .setIssuer(jwtProperties.getIssuer())
            .setIssuedAt(date)
            .setExpiration(new Date(date.getTime() + Duration.ofMinutes(
                jwtProperties.getExpiredMinute()).toMillis()))
            .claim("id",
                ((CustomUserDetails) authentication.getPrincipal()).getId())
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
            .compact();
    }

    public Authentication getAuthenticationFromToken(String tokenHeader){

        Claims claims = getClaimsFromToken(tokenHeader);
        validateClaim(claims);

        CustomUserDetails userDetails = CustomUserDetails.builder()
            .id((long) (int) claims.get("id"))
            .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", null);
    }

    private Claims getClaimsFromToken(String token) {

        //token value 추출
        validateTokenHeader(token);
        String headerDeleteToken = deleteHeaderFromToken(token);
        return parseToken(headerDeleteToken);
    }

    private Claims parseToken(String token) {

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return Jwts.parser()
            .setSigningKey(jwtProperties.getSecretKey())
            .parseClaimsJws(token)
            .getBody();

    }

    //토큰 검증
    public void validateTokenHeader(String token) {
        if (token == null || !token.startsWith(jwtProperties.getPrefix())) {
            throw new UnsupportedJwtException("Invalid header");
        }
    }

    public String deleteHeaderFromToken(String token) {
        return token.substring(jwtProperties.getPrefix().length() + 1);
    }

    private void validateClaim(Claims claims) {

        Date now = new Date();
        Date expiration = claims.getExpiration();

        if (now.after(expiration)) {
            throw new ExpiredJwtException(Jwts.header(), claims,
                ErrorCode.EXPIRED_TOKEN.getMessage());
        }
    }
}
