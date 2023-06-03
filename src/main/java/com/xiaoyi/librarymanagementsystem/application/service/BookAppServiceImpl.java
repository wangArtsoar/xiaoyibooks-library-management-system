package com.xiaoyi.librarymanagementsystem.application.service;

import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.facade.BookAppService;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.service.BookService;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.Borrow;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.BookMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

	@Override
	public List<Book> addBookList(List<BookDto> bookDtos) {
		return bookService.addBookList(bookMapper.bookDtoToBookList(bookDtos));
	}

	@Override
	public List<Book> addBookMap(Map<String, List<BookDto>> map) {
		Map<String, List<Book>> bookMap = new HashMap<>();
		map.forEach((k, v) -> bookMap.put(k, bookMapper.bookDtoToBookList(v)));
		return bookService.addBookMap(bookMap);
	}

	@Override
	public Page<Book> findAllBooks(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return bookService.findAllByPageable(pageable);
	}

	@Override
	public Page<Book> findByAssortName(int page, int size, String assortName) {
		Pageable pageable = PageRequest.of(page, size);
		return bookService.findByAssortName(pageable, assortName);
	}

	@Override
	public Book editBook(Integer id, BookDto bookDto) {
		return bookService.editBook(id, bookMapper.bookDtoToBook(bookDto));
	}

	@Override
	public Borrow borrowBook(Integer bookId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return bookService.borrowBook(email, bookId);
	}

	@Override
	public Page<Book> findAllByTemp(String temp, Pageable pageable) {
		return bookService.findAllByTemp(temp, pageable);
	}

	@Override
	public List<Borrow> findBorrowByUser(User user) {
		return bookService.getBorrowByUser(user);
	}
}
