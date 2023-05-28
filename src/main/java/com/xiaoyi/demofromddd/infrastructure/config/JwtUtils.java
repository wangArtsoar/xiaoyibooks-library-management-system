package com.xiaoyi.demofromddd.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * @author 王艺翔
 * @description JwtUtils
 * @date 2023/5/26 19:17
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Configuration
public class JwtUtils {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	public String extractUsername(String jwt) {
		return extractClaim(jwt, Claims::getSubject);
	}

	private <T> T extractClaim(String jwt, @NotNull Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(jwt);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String jwt) {
		return Jwts
						.parserBuilder()
						.setSigningKey(getSigningKey(secretKey))
						.build()
						.parseClaimsJws(jwt)
						.getBody();
	}

	private @NotNull Key getSigningKey(String secretKey) {
		// 从一个 Base64 编码的字符串 编码成 byte数组
		byte[] bytes = Decoders.BASE64.decode(secretKey);
		// 创建一个 HMAC-SHA 签名密钥
		return Keys.hmacShaKeyFor(bytes);
	}

	public boolean isTokenValid(String jwt, @NotNull UserDetails userDetails) {
		String email = extractUsername(jwt);
		return userDetails.getUsername().equals(email) &&
						extractExpiration(jwt).after(new Date());
	}

	private Date extractExpiration(String jwt) {
		return extractClaim(jwt, Claims::getExpiration);
	}

	public String generateToken(@NotNull UserDetails user) {
		return Jwts
						.builder()
						.setSubject(user.getUsername())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
						.signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
						.compact();
	}
}
