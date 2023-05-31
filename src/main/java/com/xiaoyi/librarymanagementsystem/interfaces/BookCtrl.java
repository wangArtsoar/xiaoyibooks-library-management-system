package com.xiaoyi.librarymanagementsystem.interfaces;

import com.xiaoyi.librarymanagementsystem.application.assembler.BookAssembler;
import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BookViewModel;
import com.xiaoyi.librarymanagementsystem.application.facade.BookAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 王艺翔
 * @description BookCtrl
 * @date 2023/5/29 20:49
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookCtrl {

	private final BookAppService bookAppService;

	@PostMapping("admin/bulkAddList")
	@Tag(name = "admin")
	@Operation(summary = "散装新增图书")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Map<String, List<BookViewModel>>> bulkAddList(@RequestBody List<BookDto> bookDtos) {
		return ResponseEntity.ok(BookAssembler.toBookListViewModel(bookAppService.addBookList(bookDtos)));
	}

	@PostMapping("admin/ContainerAddMap")
	@Tag(name = "admin")
	@Operation(summary = "集装新增图书")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Map<String, List<BookViewModel>>> ContainerAddMap(@RequestBody Map<String, List<BookDto>> map) {
		return ResponseEntity.ok(BookAssembler.toBookListViewModel(bookAppService.addBookMap(map)));
	}
}
