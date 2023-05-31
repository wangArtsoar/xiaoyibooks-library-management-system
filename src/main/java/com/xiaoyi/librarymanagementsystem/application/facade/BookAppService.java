package com.xiaoyi.librarymanagementsystem.application.facade;

import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @author 王艺翔
 * @description BookAppService
 * @date 2023/5/29 20:57
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface BookAppService {
	List<Book> addBookList(List<BookDto> bookDtos);

	List<Book> addBookMap(Map<String, List<BookDto>> map);
}
