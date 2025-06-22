package com.project.app.auth.component;

import com.project.app.auth.configuration.CustomUserDetails;
import com.project.app.core.exception.exceptionSub.InvalidParamException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
	@Value("${jwt.expiration}")
	private int expiration; //save to an environment variable
	@Value("${jwt.secretKey}")
	private String secretKey;
	private static final int HOUR_RESET = 24;

	public String generateToken(CustomUserDetails author) throws Exception {
		//properties => claims
		Map<String, Object> claims = new HashMap<>();
		claims.put("fullName", author.getAuthDto().getFullname());
		claims.put("userName", author.getUsername());
		claims.put("authorVersion" , author.getAuthDto().getUserVersion());
		claims.put("authorities", author.getAuthorities().stream()
				.map(role -> role.getAuthority())
				.toList());
		try {
			String token = Jwts.builder()
					.setClaims(claims)
					.setSubject(author.getUsername())
					.setExpiration(getExpirationTime())
					.signWith(getSignInKey(), SignatureAlgorithm.HS256)
					.compact();
			return token;
		}
		catch (Exception e) {
			//you can "inject" Logger, instead System.out.println
			throw new InvalidParamException("Cannot create jwt token, error: " + e.getMessage());
		}
	}
	private Date getExpirationTime() {
		Calendar calendar = Calendar.getInstance();
		// set the time to midnight (12:00 AM) of the next day
		calendar.set(Calendar.HOUR_OF_DAY, HOUR_RESET);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
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

	//check expiration
	public boolean isTokenExpired(String token) {
		Date expirationDate = this.extractClaim(token, Claims::getExpiration);
		return expirationDate.before(new Date());
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Integer extractAuthorVersion(String token) {
		return extractClaim(token, claims -> claims.get("authorVersion", Integer.class));
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		String author = extractUserName(token);
		Integer authorVersionFromToken = extractAuthorVersion(token);
		Integer authorVersionFromSystem =((CustomUserDetails)userDetails).getAuthDto().getUserVersion();
		return (
				author.equals(userDetails.getUsername())
				&& !isTokenExpired(token)
				&& authorVersionFromToken.equals(authorVersionFromSystem)
		);
	}
}
