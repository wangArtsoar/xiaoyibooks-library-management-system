package com.xiaoyi.librarymanagementsystem.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description KeyIncorrectException
 * @date 2023/5/29 12:16
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@RequiredArgsConstructor
public class KeyIncorrectException extends RuntimeException {
	@Getter
	private final String message;
}
