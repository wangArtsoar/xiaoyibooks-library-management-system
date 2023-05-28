package com.xiaoyi.demofromddd.domain.user.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author 王艺翔
 * @description Role
 * @date 2023/5/26 15:02
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@RequiredArgsConstructor
public enum Role {
	ADMIN("admin"),
	USER("user");
	@Getter
	private final String role;

	public List<SimpleGrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.name()));
	}
}
