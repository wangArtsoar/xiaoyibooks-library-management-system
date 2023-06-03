package com.xiaoyi.librarymanagementsystem.infrastructure.config;

import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王艺翔
 * @description MapperConfig
 * @date 2023/6/3 11:45
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Configuration
public class MapperConfig {
	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}

	@Bean
	public AssortMapper assortMapper() {
		return Mappers.getMapper(AssortMapper.class);
	}

	@Bean
	public BookMapper bookMapper() {
		return Mappers.getMapper(BookMapper.class);
	}

	@Bean
	public BorrowMapper borrowMapper() {
		return Mappers.getMapper(BorrowMapper.class);
	}

	@Bean
	public PageMapper pageMapper() {
		return Mappers.getMapper(PageMapper.class);
	}
}
