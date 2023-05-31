package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Assort;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description BookFactory
 * @date 2023/5/29 21:02
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public class BookFactory {

	public static List<AssortPo> toAssortPoList(List<Assort> assorts) {
		return assorts.stream()
						.map(BookFactory::toAssortPo)
						.collect(Collectors.toList());
	}

	public static AssortPo toAssortPo(Assort assort) {
		return AssortPo
						.builder()
						.id(assort.getId())
						.name(assort.getName())
						.address(assort.getAddress())
						.bookAmount(assort.getBookAmount())
						.build();
	}

	public static List<BookPo> toBookPoList(List<Book> books) {
		return books.stream()
						.map(BookFactory::toBookPo)
						.collect(Collectors.toList());
	}

	public static BookPo toBookPo(Book book) {
		return BookPo
						.builder()
						.name(book.getName())
						.author(book.getAuthor())
						.assortName(book.getAssortName())
						.address(book.getAddress())
						.publishDate(book.getPublishDate())
						.createAt(book.getCreateAt())
						.build();
	}

	public static Assort toAssort(AssortPo assortPo) {
		Assort assort = new Assort();
		BeanUtils.copyProperties(assortPo, assort);
		return assort;
	}

	public static List<Book> toBookList(List<BookPo> bookPos) {
		return bookPos.stream()
						.map(BookFactory::toBook)
						.collect(Collectors.toList());
	}

	public static Book toBook(BookPo bookPo) {
		Book book = new Book();
		BeanUtils.copyProperties(bookPo, book);
		return book;
	}
}
