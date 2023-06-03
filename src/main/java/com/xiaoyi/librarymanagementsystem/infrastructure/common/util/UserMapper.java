package com.xiaoyi.librarymanagementsystem.infrastructure.common.util;

import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserDetailViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserViewModel;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import org.mapstruct.Mapper;

/**
 * @author 王艺翔
 * @description UserAssembler
 * @date 2023/5/26 17:30
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper
public interface UserMapper {
	User registerDtoToUser(RegisterDto registerDto);

	UserViewModel userToUserViewModel(User user);

	User EditUserDtoToUser(EditUserDto userDto);

	UserDetailViewModel userToUserDetailViewModel(User user);

	User userPoToUser(UserPo userPo);

	UserPo userToUserPo(User user);
}
