package com.xiaoyi.demofromddd.domain.user.repository.info;

import com.xiaoyi.demofromddd.domain.user.valueobject.Role;

/**
 * A Projection for the {@link com.xiaoyi.demofromddd.domain.user.repository.po.UserPo} entity
 */
public interface UserPoInfo {
	String getName();

	String getEmail();

	Role getRole();

}