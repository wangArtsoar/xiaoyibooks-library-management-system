package com.xiaoyi.librarymanagementsystem.application.dto.viewmodel;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 王艺翔
 * @description BorrowSimpleVo
 * @date 2023/6/3 11:35
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public record BorrowSimpleVo(String bookName,
                             @DateTimeFormat(
				                             iso = DateTimeFormat.ISO.DATE_TIME,
				                             pattern = "yyyy-MM-dd")
                             Date borrowDate) {
}
