package com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers;

import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BookViewModel;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description PageMapper
 * @date 2023/6/3 11:22
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper
public interface PageMapper {
	BookViewModel bookToBookViewModel(Book bookPage);

	Book bookPoToBook(BookPo bookPo);

	default Page<BookViewModel> pageBookToPageBookViewModel(Page<Book> bookPage) {
		List<BookViewModel> bookViewModels = bookPage.getContent().stream()
						.map(this::bookToBookViewModel)
						.collect(Collectors.toList());
		return new PageImpl<>(bookViewModels, bookPage.getPageable(), bookPage.getTotalElements());
	}

	default Page<Book> pageBookPoToPageBook(Page<BookPo> bookPage) {
		List<Book> books = bookPage.getContent().stream()
						.map(this::bookPoToBook)
						.collect(Collectors.toList());
		return new PageImpl<>(books, bookPage.getPageable(), bookPage.getTotalElements());
	}
}
