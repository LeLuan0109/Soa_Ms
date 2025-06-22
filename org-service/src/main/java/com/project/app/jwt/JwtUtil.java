package com.project.app.jwt;

import com.project.app.core.exception.exceptionSub.InvalidParamException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.expiration}")
    private int expiration; //save to an environment variable
    @Value("${jwt.secretKey}")
    private String secretKey = "TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI=";
    private static final int HOUR_RESET = 24;

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public List<SimpleGrantedAuthority> extractAuthorities(String token) throws InvalidParamException {
        try {
            Claims claims = this.extractAllClaims(token);
            return ((List<?>) claims.get("authorities")).stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Cannot extract authorities from token: {}", e.getMessage());
            throw new InvalidParamException("Invalid token - cannot extract authorities");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}