package com.xiaoyi.librarymanagementsystem.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description RedisNotFoundException
 * @date 2023/5/29 12:21
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@RequiredArgsConstructor
public class RedisNotFoundException extends RuntimeException {
	@Getter
	private final String message;
}
