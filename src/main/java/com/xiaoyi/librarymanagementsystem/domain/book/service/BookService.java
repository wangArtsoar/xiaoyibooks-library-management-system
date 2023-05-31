package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @author 王艺翔
 * @description BookService
 * @date 2023/5/29 21:01
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface BookService {
	List<Book> addBookList(List<Book> books);

	List<Book> addBookMap(Map<String, List<Book>> map);
}
