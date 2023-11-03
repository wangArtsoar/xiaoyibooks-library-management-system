package com.xiaoyi.librarymanagementsystem.infrastructure.auth;

import com.xiaoyi.librarymanagementsystem.application.dto.LoginDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.AuthViewModel;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.TokenRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.TokenPo;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.UserMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 王艺翔
 * @description AuthenticationService
 * @date 2023/5/27 12:11
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager manager;
	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final TokenRepository tokenRepository;

	public String register(RegisterDto registerDto) {
		User user = userMapper.registerDtoToUser(registerDto);
		user.setPwd(passwordEncoder.encode(user.getPwd()));
		user.setRole(Role.USER);
		UserPo userPo = userRepository.save(userMapper.userToUserPo(user));
		String token = jwtUtils.generateToken(userPo);
		var tokenPo = TokenPo
						.builder()
						.token(token)
						.invalid(false)
						.userPo(userPo)
						.build();
		tokenRepository.save(tokenPo);
		return "注册成功";
	}

	public AuthViewModel login(LoginDto loginDto) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(
						loginDto.email(), loginDto.password())
		);
		UserPo userPo = userRepository.findByEmail(loginDto.email()).orElseThrow();
		String token = jwtUtils.generateToken(userPo);
		tokenRepository.findByUserEmail(userPo.getEmail()).ifPresent(
						tokenPo -> tokenPo.setInvalid(Boolean.TRUE)
		);
		tokenRepository.save(TokenPo.builder().token(token).userPo(userPo).build());
		return new AuthViewModel(token, userPo.getRole().equals(Role.ADMIN));
	}

	
}
