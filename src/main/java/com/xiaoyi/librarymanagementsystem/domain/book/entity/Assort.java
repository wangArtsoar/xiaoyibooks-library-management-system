package com.xiaoyi.librarymanagementsystem.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description Assort
 * @date 2023/5/29 18:22
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assort {
	private Integer id;
	private String name;
	private List<Book> books;
	private String address;
}
