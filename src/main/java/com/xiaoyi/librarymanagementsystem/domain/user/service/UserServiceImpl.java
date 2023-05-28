package com.xiaoyi.librarymanagementsystem.domain.user.service;

import com.xiaoyi.librarymanagementsystem.application.dto.ChangePwdDto;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.xiaoyi.librarymanagementsystem.domain.user.service.UserFactory.toUser;

/**
 * @author 王艺翔
 * @description UserServiceImpl
 * @date 2023/5/26 17:34
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User editUser(String email, User user) {
		UserPo userPo = userRepository.findByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		userPo.setName(user.getName());
		userPo.setEmail(user.getEmail());
		return toUser(userRepository.save(userPo));
	}

	@Override
	public boolean changePassword(String email, ChangePwdDto changePwd) {
		UserPo userPo = userRepository.findByEmail(email).orElseThrow();
		if (passwordEncoder.matches(changePwd.oldPassword(), userPo.getPassword())) return false;
		userPo.setPwd(passwordEncoder.encode(changePwd.newPassword()));
		userRepository.save(userPo);
		return true;
	}

	@Override
	public String getChangeKey(String email) {
		UserPo userPo = userRepository.findByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		if (Role.ADMIN.name().equals(userPo.getRole().name())) return email + "已经是admin";
		String s = "welcomebeaadmin" + userPo.getEmail() + userPo.getPwd();
		return passwordEncoder.encode(s);
	}

	@Override
	public boolean upgradeRole(String email, String redisValue, String key) {
		if (key.equals(redisValue)) return false;
		UserPo userPo = userRepository.findByEmail(email).orElseThrow();
		userPo.setRole(Role.ADMIN);
		userRepository.save(userPo);
		return true;
	}
}
