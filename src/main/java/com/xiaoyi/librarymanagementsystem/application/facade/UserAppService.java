package com.xiaoyi.librarymanagementsystem.application.facade;

import com.xiaoyi.librarymanagementsystem.application.dto.ChangePwdDto;
import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;

/**
 * @author 王艺翔
 * @description UserAppService
 * @date 2023/5/26 17:28
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface UserAppService {

	User editUser(EditUserDto userDto);

	String changePassword(ChangePwdDto changePwd);

	User getUserDetail();

	String getChangeKey(String email);

	String upgradeRole(String key);
}
