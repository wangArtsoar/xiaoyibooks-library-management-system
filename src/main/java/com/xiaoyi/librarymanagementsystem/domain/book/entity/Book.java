package com.xiaoyi.librarymanagementsystem.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 王艺翔
 * @description Book
 * @date 2023/5/28 14:11
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	private Integer id;
	private String name;
	private String author;
	private boolean isBorrow;
	private boolean isDelete;
	private Date publishDate;
	private Date createAt;
	private Date updateAt;
	private String address;
	private String assortName;
	private String filePath;
}
