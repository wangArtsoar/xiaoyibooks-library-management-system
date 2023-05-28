package com.xiaoyi.librarymanagementsystem.domain.user.service;

import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;

/**
 * @author 王艺翔
 * @description UserFactory
 * @date 2023/5/26 15:18
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public class UserFactory {
	public static UserPo toUserPo(User user) {
		return UserPo
						.builder()
						.name(user.getName())
						.email(user.getEmail())
						.pwd(user.getPwd())
						.role(user.getRole())
						.build();
	}

	public static User toUser(UserPo userPo) {
		return User
						.builder()
						.id(userPo.getId())
						.email(userPo.getEmail())
						.name(userPo.getName())
						.role(userPo.getRole())
						.build();
	}
}
