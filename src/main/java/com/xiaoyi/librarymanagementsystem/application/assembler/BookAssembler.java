package com.xiaoyi.librarymanagementsystem.application.assembler;

import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BookViewModel;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description BookAssembler
 * @date 2023/5/29 20:58
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public class BookAssembler {

	public static BookViewModel toBookViewModel(Book book) {
		return new BookViewModel(
						book.getName(),
						book.getAssortName(),
						book.getAddress(),
						book.getAuthor(),
						book.getCreateAt(),
						book.getPublishDate());
	}

	public static Book toBook(BookDto bookDto) {
		return Book
						.builder()
						.name(bookDto.name())
						.author(bookDto.author())
						.assortName(bookDto.assort())
						.publishDate(bookDto.publishDate())
						.build();
	}

	public static Map<String, List<BookViewModel>> toBookListViewModel(List<Book> books) {
		return books
						.stream()
						.map(BookAssembler::toBookViewModel)
						.collect(Collectors.groupingBy(BookViewModel::assort));
	}

	public static List<Book> toBookList(List<BookDto> bookDtos) {
		return bookDtos
						.stream()
						.map(BookAssembler::toBook)
						.collect(Collectors.toList());
	}
}
