package com.xiaoyi.librarymanagementsystem.infrastructure.auth;

import com.xiaoyi.librarymanagementsystem.application.assembler.UserAssembler;
import com.xiaoyi.librarymanagementsystem.application.dto.LoginDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import com.xiaoyi.librarymanagementsystem.domain.user.service.UserFactory;
import com.xiaoyi.librarymanagementsystem.infrastructure.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
	private final RedisTemplate<String, String> redisTemplate;
	private final PasswordEncoder passwordEncoder;

	public String register(RegisterDto registerDto) {
		User user = UserAssembler.toRegister(registerDto);
		user.setPwd(passwordEncoder.encode(user.getPwd()));
		UserPo userPo = userRepository.save(UserFactory.toUserPo(user));
		String token = jwtUtils.generateToken(userPo);
		redisTemplate.opsForValue().append(user.getEmail(), token);
		return "注册成功";
	}

	public String login(LoginDto loginDto) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
		UserPo userPo = userRepository.findByEmail(loginDto.email()).orElseThrow();
		redisTemplate.delete(userPo.getUsername());
		String token = jwtUtils.generateToken(userPo);
		redisTemplate.opsForValue().set(userPo.getUsername(), token, 1, TimeUnit.DAYS);
		return token;
	}
}
