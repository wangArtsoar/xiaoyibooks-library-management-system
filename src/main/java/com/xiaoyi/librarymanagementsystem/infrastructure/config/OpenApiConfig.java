package com.xiaoyi.librarymanagementsystem.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author 王艺翔
 * @description OpenApiConfig
 * @date 2023/5/27 23:40
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@OpenAPIDefinition(
				// 定义安全需求
				security = {@SecurityRequirement(name = "Authorization")})
// 定义安全方案
@SecurityScheme(
				type = SecuritySchemeType.HTTP,     // 定义类型为HTTP
				name = "Authorization",             // 名称
				description = "Authorization token",// 简介
				scheme = "bearer",                  // 前缀
				bearerFormat = "JWT",               // 承载格式
				in = SecuritySchemeIn.HEADER        // 定义位置
)

public class OpenApiConfig {
}
