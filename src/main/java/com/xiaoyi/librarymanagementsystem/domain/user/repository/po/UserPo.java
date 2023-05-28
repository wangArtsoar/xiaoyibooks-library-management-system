package com.xiaoyi.librarymanagementsystem.domain.user.repository.po;

import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author 王艺翔
 * @description UserPo
 * @date 2023/5/26 15:00
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPo implements UserDetails {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Column(unique = true)
	private String email;
	private String pwd;
	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getPassword() {
		return pwd;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
