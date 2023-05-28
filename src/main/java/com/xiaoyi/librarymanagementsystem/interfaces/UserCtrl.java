package com.xiaoyi.librarymanagementsystem.interfaces;

import com.xiaoyi.librarymanagementsystem.application.assembler.UserAssembler;
import com.xiaoyi.librarymanagementsystem.application.dto.ChangePwdDto;
import com.xiaoyi.librarymanagementsystem.application.dto.EditUserDto;
import com.xiaoyi.librarymanagementsystem.application.dto.LoginDto;
import com.xiaoyi.librarymanagementsystem.application.dto.RegisterDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserDetailViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.UserViewModel;
import com.xiaoyi.librarymanagementsystem.application.facade.UserAppService;
import com.xiaoyi.librarymanagementsystem.infrastructure.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 王艺翔
 * @description DemoCtrl
 * @date 2023/5/26 14:23
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection">...</a>"
 */
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserCtrl {

	private final UserAppService userAppService;
	private final AuthenticationService authenticationService;

	@PostMapping("auth/register")
	@Tag(name = "auth", description = "认证")
	@Operation(summary = "注册")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authenticationService.register(registerDto));
	}

	@PostMapping("auth/login")
	@Tag(name = "auth")
	@Operation(summary = "登录")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		return ResponseEntity.ok(authenticationService.login(loginDto));
	}

	@PostMapping("edit")
	@Tag(name = "user", description = "用户")
	@Operation(summary = "修改信息")
	public ResponseEntity<UserViewModel> editUser(@RequestBody EditUserDto editUserDto) {
		return ResponseEntity.ok(UserAssembler.toUserViewModel(userAppService.editUser(editUserDto)));
	}

	@PostMapping("changePassword")
	@Tag(name = "user")
	@Operation(summary = "修改密码")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePwdDto changePwd) {
		return ResponseEntity.ok(userAppService.changePassword(changePwd));
	}

	@GetMapping("getUserDetail")
	@Tag(name = "user")
	@Operation(summary = "用户详情")
	public ResponseEntity<UserDetailViewModel> getUserDetail() {
		return ResponseEntity.ok(UserAssembler.toUserDetailViewModel(userAppService.getUserDetail()));
	}

	@PostMapping("admin/getUpgradeKey")
	@Tag(name = "admin", description = "管理员")
	@Operation(summary = "获取升级管理的密钥")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ChangeKey> changeUserRole(@RequestBody ChangeBody changeBody) {
		return ResponseEntity.ok(toChangeKey(userAppService.getChangeKey(changeBody.email)));
	}

	@PutMapping("upgradeRole{key}")
	@Tag(name = "user")
	@Operation(summary = "升级角色")
	public ResponseEntity<String> upgradeRole(@PathVariable @Schema(description = "升级密钥") String key) {
		return ResponseEntity.ok(userAppService.upgradeRole(key));
	}

	private ChangeKey toChangeKey(String key) {
		return new ChangeKey(key, new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
	}

	record ChangeKey(String key, Date expiration) {
	}

	record ChangeBody(String email) {
	}
}

