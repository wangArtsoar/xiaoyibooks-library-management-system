package com.xiaoyi.librarymanagementsystem.domain.user.repository.info;

import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;

/**
 * A Projection for the {@link com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo} entity
 */
public interface UserPoInfo {
	String getName();

	String getEmail();

	Role getRole();

}