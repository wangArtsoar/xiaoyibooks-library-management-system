package com.xiaoyi.librarymanagementsystem.application.dto.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 王艺翔
 * @description AuthViewModel
 * @date 2023/6/5 14:55
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public record AuthViewModel(@JsonFormat String token, boolean isAdmin) {
}
