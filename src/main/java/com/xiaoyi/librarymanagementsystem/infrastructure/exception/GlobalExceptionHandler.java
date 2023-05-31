package com.xiaoyi.librarymanagementsystem.infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * @author 王艺翔
 * @description GlobalExceptionHandler
 * @date 2023/5/29 11:40
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = PasswordIncorrectException.class)
	@ResponseBody
	public ResponseEntity<String> handlePasswordIncorrectException(PasswordIncorrectException e) {
		return ResponseEntity.status(UNAUTHORIZED).body(e.getMessage());
	}

	//UsernameNotFoundException
	@ExceptionHandler(value = UsernameNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
		return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
	}

	//KeyIncorrectException
	@ExceptionHandler(value = KeyIncorrectException.class)
	@ResponseBody
	public ResponseEntity<String> handleKeyIncorrectException(KeyIncorrectException e) {
		return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
	}

	//RedisNotFoundException
	@ExceptionHandler(value = RedisNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleRedisNotFoundException(RedisNotFoundException e) {
		return ResponseEntity.status(FORBIDDEN).body(e.getMessage());
	}

	@ExceptionHandler(value = CreateFailException.class)
	@ResponseBody
	public ResponseEntity<String> handleCreateFailException(CreateFailException e) {
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
