package com.xiaoyi.librarymanagementsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Indexed;

import java.io.IOException;

@SpringBootApplication
@Indexed
@RequiredArgsConstructor
public class LibraryManagementSystemApplication {

	private final RedisTemplate<String, Object> redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
		// 在项目启动后自动打开登录页面
		try {
			Runtime.getRuntime().exec("cmd /c start http://localhost:8080/html/bookPage.html");
		} catch (IOException e) {
			throw new RuntimeException("无法在浏览器中打开 http://localhost:8080/index.html" + e);
		}

	}
}
