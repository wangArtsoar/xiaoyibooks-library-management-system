package com.xiaoyi.librarymanagementsystem.application.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 王艺翔
 * @description BookDto
 * @date 2023/5/29 20:53
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public record BookDto(String name, String author, String assortName,
                      @DateTimeFormat(
				                      iso = DateTimeFormat.ISO.DATE
                      ) Date publishDate) {
}
