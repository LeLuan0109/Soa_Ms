package com.project.app.auth.component;

import com.project.app.auth.dto.AuthDto;
import com.project.app.core.exception.exceptionSub.InvalidParamException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenForgotPasswordUtils {

    @Value("${jwt.expirationForgotPass}")
    private int expiration; //save to an environment variable
    @Value("${jwt.secretKeyForgotPass}")
    private String secretKey;

    public String generateToken(AuthDto author) throws Exception {
        //properties => claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", author.getEmail());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) //how to extract claims from this ?
                    .setSubject(author.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
//                    .setExpiration(getExpirationTime())
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }
        catch (Exception e) {
            throw new InvalidParamException("Cannot create jwt token, error: " + e.getMessage());
        }
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
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

    //check expiration
    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, AuthDto authDto) {
        String author = extractUserName(token);
        return (author.equals(authDto.getUsername()) && !isTokenExpired(token));
    }

}
