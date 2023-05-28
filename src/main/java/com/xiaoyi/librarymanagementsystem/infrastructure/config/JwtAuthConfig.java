package com.xiaoyi.librarymanagementsystem.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * @author 王艺翔
 * @description JwtAuthConfig
 * @date 2023/5/26 19:18
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Configuration
@RequiredArgsConstructor
public class JwtAuthConfig extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	protected void doFilterInternal(
					@NonNull HttpServletRequest request,
					@NonNull HttpServletResponse response,
					@NonNull FilterChain filterChain) throws ServletException, IOException {
		if (request.getServletPath().contains("/api/user/auth/")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String jwt;
		String header = request.getHeader("Authorization");
		if (header==null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = header.substring(7);
		String email = jwtUtils.extractUsername(jwt);
		SecurityContext context = SecurityContextHolder.getContext();
		if (email.isBlank() || Optional.ofNullable(context.getAuthentication()).isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		if (Boolean.FALSE.equals(redisTemplate.hasKey(email)) || !jwtUtils.isTokenValid(jwt, userDetails)) {
			filterChain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(
										userDetails.getUsername(), null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		context.setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);
	}
}
