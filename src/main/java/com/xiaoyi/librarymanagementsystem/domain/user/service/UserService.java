package com.xiaoyi.librarymanagementsystem.domain.user.service;

import com.xiaoyi.librarymanagementsystem.application.dto.ChangePwdDto;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;

/**
 * @author 王艺翔
 * @description UserService
 * @date 2023/5/26 17:33
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface UserService {

	User editUser(String email, User user);

	boolean changePassword(String email, ChangePwdDto changePwd);

	String getChangeKey(String email);

	boolean upgradeRole(String email, String redisValue, String key);
}
