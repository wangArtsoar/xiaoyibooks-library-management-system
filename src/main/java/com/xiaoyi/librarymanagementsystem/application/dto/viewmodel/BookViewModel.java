package com.xiaoyi.librarymanagementsystem.application.dto.viewmodel;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 王艺翔
 * @description BookViewModel
 * @date 2023/5/29 20:53
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public record BookViewModel(int id, String name, String assortName, String address, String author,String filePath,
                            @DateTimeFormat(
				                            iso = DateTimeFormat.ISO.DATE,
				                            pattern = "yyyy/MM/dd"
                            ) Date createAt,
                            @DateTimeFormat(
				                            iso = DateTimeFormat.ISO.DATE,
				                            pattern = "yyyy/MM/dd"
                            ) Date publishDate) {
}
