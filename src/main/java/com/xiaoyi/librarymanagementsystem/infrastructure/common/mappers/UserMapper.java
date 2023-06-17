package com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers;

import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BorrowSimpleVo;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BorrowViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserDetailViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserViewModel;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description UserAssembler
 * @date 2023/5/26 17:30
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper(uses = {BorrowMapper.class})
public interface UserMapper {
	User registerDtoToUser(RegisterDto registerDto);

	UserViewModel userToUserViewModel(User user);

	User EditUserDtoToUser(EditUserDto userDto);

	User userPoToUser(UserPo userPo);

	UserPo userToUserPo(User user);

	default UserDetailViewModel userToUserDetailViewModel(User user) {
		BorrowMapper mapper = Mappers.getMapper(BorrowMapper.class);
		Map<String, List<BorrowViewModel>> map =
						user.getBorrows()
										.stream()
										.map(mapper::borrowToBorrowViewModel)
										.collect(Collectors.groupingBy(BorrowViewModel::assortName));
		Map<String, List<BorrowSimpleVo>> borrowSimpleVoMap = new HashMap<>();
		map.forEach((k, v) -> borrowSimpleVoMap.put(k, mapper.borrowViewModelToSimpleList(v)));
		return new UserDetailViewModel(user.getName(), user.getEmail(), borrowSimpleVoMap);
	}
}
