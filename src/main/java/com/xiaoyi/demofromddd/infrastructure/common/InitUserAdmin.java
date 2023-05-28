package com.xiaoyi.demofromddd.infrastructure.common;

import com.xiaoyi.demofromddd.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.demofromddd.domain.user.repository.po.UserPo;
import com.xiaoyi.demofromddd.domain.user.valueobject.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 王艺翔
 * @description InitUserAdmin
 * @date 2023/5/28 16:39
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Component
@RequiredArgsConstructor
public class InitUserAdmin implements ApplicationListener<ContextRefreshedEvent> {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (userRepository.countByRole(Role.ADMIN) > 0) {
			return;
		}
		userRepository.save(UserPo
						.builder()
						.name("init_admin")
						.email("init_admin@admin.com")
						.role(Role.ADMIN)
						.pwd(passwordEncoder.encode("initAdmin"))
						.build());
	}
}
