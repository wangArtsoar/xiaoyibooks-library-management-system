package com.xiaoyi.librarymanagementsystem.infrastructure.config;

import com.xiaoyi.librarymanagementsystem.domain.user.valueobject.Role;
import com.xiaoyi.librarymanagementsystem.infrastructure.auth.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 王艺翔
 * @description SecurityConfig
 * @date 2023/5/26 18:04
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthConfig jwtAuthConfig;
	private final LogoutService logoutService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
						.csrf(AbstractHttpConfigurer::disable)
						.authorizeHttpRequests((request) -> request
										.requestMatchers(
												"files/**",
														"image/**",
														"favicon.ico",
														"/css/**",
														"index.html",
														"/js/**",
														"/html/**",
														"/api/user/auth/**",
														"/v2/api-docs",
														"/v3/api-docs",
														"/v3/api-docs/**",
														"/swagger-resources",
														"/swagger-resources/**",
														"/configuration/ui",
														"/configuration/security",
														"/swagger-ui/**",
														"/webjars/**",
														"/swagger-ui.html")
										.permitAll()
										.requestMatchers("/api/user/admin/**").hasAuthority(Role.ADMIN.name())
										.requestMatchers("/api/book/admin/**").hasAuthority(Role.ADMIN.name())
										.anyRequest()
										.authenticated())
						.sessionManagement(session -> session
										.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.authenticationProvider(authenticationProvider)
						.addFilterBefore(jwtAuthConfig, UsernamePasswordAuthenticationFilter.class)
						.logout(logout -> logout
										.logoutUrl("/api/user/auth/logout")
										.addLogoutHandler(logoutService)
										.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

		return http.build();
	}
}
