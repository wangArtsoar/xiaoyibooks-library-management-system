package com.xiaoyi.librarymanagementsystem.domain.user.entity;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description Borrow
 * @date 2023/5/29 18:39
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrow {
	private Integer id;
	private User user;
	private Book book;
}
