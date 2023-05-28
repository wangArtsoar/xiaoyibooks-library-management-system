package com.xiaoyi.demofromddd.domain.user.repository.persistence;

import com.xiaoyi.demofromddd.domain.user.repository.info.UserPoInfo;
import com.xiaoyi.demofromddd.domain.user.repository.po.UserPo;
import com.xiaoyi.demofromddd.domain.user.valueobject.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author 王艺翔
 * @description UserRepository
 * @date 2023/5/26 15:13
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface UserRepository extends JpaRepository<UserPo, Integer> {

	@Query("SELECT u.id FROM UserPo u WHERE u.email =:email")
	Integer findIdByEmail(String email);

	Optional<UserPo> findByEmail(String email);

	@Query("select u.name,u.email,u.role from UserPo u")
	Optional<UserPoInfo> findUserPoByEmail(String email);

	int countByRole(Role role);
}
