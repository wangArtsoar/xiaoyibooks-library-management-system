package com.xiaoyi.librarymanagementsystem.application.service;

import com.xiaoyi.librarymanagementsystem.application.assembler.UserAssembler;
import com.xiaoyi.librarymanagementsystem.application.dto.ChangePwdDto;
import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.application.facade.UserAppService;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 王艺翔
 * @description UserAppServiceImpl
 * @date 2023/5/26 17:30
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {

	private final UserService userService;
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public User editUser(EditUserDto editUserDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userService.editUser(authentication.getName(), UserAssembler.toEditUserDto(editUserDto));
	}

	@Override
	public String changePassword(ChangePwdDto changePwd) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		if (!userService.changePassword(email, changePwd)) {
			return "修改密码失败";
		}
		// 删除token
		redisTemplate.delete(email);
		// 删除上下文
		SecurityContextHolder.clearContext();
		return "修改密码成功，请重新登录";
	}

	@Override
	public User getUserDetail() {
		// TODO
		return null;
	}

	@Override
	public String getChangeKey(String email) {
		String key = userService.getChangeKey(email);
		redisTemplate.opsForValue().set("upgrade" + email, key, 1, TimeUnit.DAYS);
		return key;
	}

	@Override
	public String upgradeRole(String key) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String redisKey = "upgrade" + email;
		if (Boolean.FALSE.equals(redisTemplate.hasKey(redisKey))) return "密钥不正确，升级失败";
		String redisValue = redisTemplate.opsForValue().get(redisKey);
		if (!userService.upgradeRole(email, redisValue, key)) return "密钥不正确，升级失败";
		redisTemplate.delete(redisKey);
		return "升级成功";
	}
}
