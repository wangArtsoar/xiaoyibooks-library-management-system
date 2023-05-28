package com.xiaoyi.librarymanagementsystem.application.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo} entity
 */
public record RegisterDto(String name, String email, String pwd) implements Serializable {
}