package com.xiaoyi.librarymanagementsystem.application.assembler;

import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserDetailViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserViewModel;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;

/**
 * @author 王艺翔
 * @description UserAssembler
 * @date 2023/5/26 17:30
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public class UserAssembler {

	public static User toRegister(RegisterDto registerDto) {
		return User.builder()
						.name(registerDto.name())
						.pwd(registerDto.pwd())
						.email(registerDto.email())
						.role(Role.USER)
						.build();
	}

	public static UserViewModel toUserViewModel(User user) {
		return new UserViewModel(user.getName(), user.getEmail());
	}

	public static User toEditUserDto(EditUserDto userDto) {
		return User
						.builder()
						.name(userDto.name())
						.email(userDto.email())
						.build();
	}

	public static UserDetailViewModel toUserDetailViewModel(User user) {
		// TODO
		return null;
	}
}
