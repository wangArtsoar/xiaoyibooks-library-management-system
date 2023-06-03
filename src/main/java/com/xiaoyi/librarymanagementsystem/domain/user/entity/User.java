package com.xiaoyi.librarymanagementsystem.domain.user.entity;

import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description User
 * @date 2023/5/26 15:14
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String name;
	private String email;
	private String pwd;
	private Role role;
	private List<Borrow> borrows;
}
