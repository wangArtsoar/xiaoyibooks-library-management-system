package com.xiaoyi.librarymanagementsystem.application.service;

import com.xiaoyi.librarymanagementsystem.application.assembler.BookAssembler;
import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.facade.BookAppService;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王艺翔
 * @description BookAppServiceImpl
 * @date 2023/5/29 20:57
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class BookAppServiceImpl implements BookAppService {

	private final BookService bookService;

	@Override
	public List<Book> addBookList(List<BookDto> bookDtos) {
		return bookService.addBookList(BookAssembler.toBookList(bookDtos));
	}

	@Override
	public List<Book> addBookMap(Map<String, List<BookDto>> map) {
		Map<String, List<Book>> bookMap = new HashMap<>();
		map.forEach((k, v) -> bookMap.put(k, BookAssembler.toBookList(v)));
		return bookService.addBookMap(bookMap);
	}
}
