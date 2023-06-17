package com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers;

import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BorrowSimpleVo;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BorrowViewModel;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.Borrow;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.BorrowPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 王艺翔
 * @description BorrowMapper
 * @date 2023/6/3 11:20
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper(uses = {UserMapper.class, BookMapper.class})
public interface BorrowMapper {
	@Mappings({
					@Mapping(source = "user.name", target = "name"),
					@Mapping(source = "user.email", target = "email"),
					@Mapping(source = "book.assortName", target = "assortName"),
					@Mapping(source = "book.name", target = "bookName")
	})
	BorrowViewModel borrowToBorrowViewModel(Borrow borrow);

	@Mapping(source = "userPo", target = "user")
	@Mapping(source = "bookPo", target = "book")
	Borrow borrowPoToBorrow(BorrowPo borrowPo);

	List<Borrow> borrowPoToBorrowList(List<BorrowPo> borrowPos);

	List<BorrowSimpleVo> borrowViewModelToSimpleList(List<BorrowViewModel> borrows);
}
