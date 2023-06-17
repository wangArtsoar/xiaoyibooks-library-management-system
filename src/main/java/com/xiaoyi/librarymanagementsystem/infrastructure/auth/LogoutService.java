package com.xiaoyi.librarymanagementsystem.infrastructure.auth;

import com.xiaoyi.librarymanagementsystem.infrastructure.config.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * @author 王艺翔
 * @description LogoutService
 * @date 2023/5/28 11:14
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

	private final RedisTemplate<String, String> redisTemplate;
	private final JwtUtils jwtUtils;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		if (authHeader==null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		jwt = authHeader.substring(7);
		String email = jwtUtils.extractUsername(jwt);
		redisTemplate.delete(email);
		SecurityContextHolder.clearContext();
	}
}
