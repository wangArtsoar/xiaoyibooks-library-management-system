package com.xiaoyi.librarymanagementsystem.domain.user.entity;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description Reader
 * @date 2023/5/29 20:46
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reader {
	private User user;
	private List<Book> books;
}
