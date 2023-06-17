package com.xiaoyi.librarymanagementsystem.infrastructure.auth;

import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.TokenRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.TokenPo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

	private final TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		if (authHeader==null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		jwt = authHeader.substring(7);
		TokenPo tokenPo = tokenRepository.findByToken(jwt).orElse(null);
		if (tokenPo!=null) {
			tokenPo.setInvalid(true);
			tokenRepository.save(tokenPo);
			SecurityContextHolder.clearContext();
		}
	}
}
