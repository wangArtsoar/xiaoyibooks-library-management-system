package com.xiaoyi.librarymanagementsystem.interfaces;

import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BookViewModel;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BorrowViewModel;
import com.xiaoyi.librarymanagementsystem.application.facade.BookAppService;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.BookMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.BorrowMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.PageMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
    private final BorrowMapper borrowMapper = Mappers.getMapper(BorrowMapper.class);
    private final PageMapper pageMapper = Mappers.getMapper(PageMapper.class);

    @PostMapping("admin/addBook")
    @Tag(name = "admin")
    @Operation(summary = "新增图书")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<BookViewModel> addBook(@ModelAttribute BookDto bookDto) {
        return ResponseEntity.ok(bookMapper.bookToBookViewModel(bookAppService.addBook(bookDto)));
    }

    @GetMapping("bookPage")
    @Tag(name = "book")
    @Operation(summary = "图书分页")
    public Page<BookViewModel> findAllBooksPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "21") int size) {
        return pageMapper.pageBookToPageBookViewModel(bookAppService.findAllBooks(page, size));
    }

    @GetMapping("findByAssortNamePage")
    @Tag(name = "book", description = "图书")
    @Operation(summary = "根据图书类别分页")
    public Page<BookViewModel> findByAssortName(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "14") int size,
            @RequestParam(value = "assortName") String assortName) {
        return pageMapper.pageBookToPageBookViewModel(bookAppService.findByAssortName(page, size, assortName));
    }

    @PutMapping("admin/edit{id}")
    @Tag(name = "admin")
    @Operation(summary = "修改图书")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<BookViewModel> editById(
            @PathVariable Integer id, @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookMapper.bookToBookViewModel(bookAppService.editBook(id, bookDto)));
    }

    @PostMapping("borrowBook{bookId}")
    @Tag(name = "book")
    @Operation(summary = "借阅图书")
    public ResponseEntity<BorrowViewModel> borrowBook(@PathVariable Integer bookId) {
        return ResponseEntity.ok(borrowMapper.borrowToBorrowViewModel(bookAppService.borrowBook(bookId)));
    }

    @GetMapping("searchBook{temp}")
    @Tag(name = "book")
    @Operation(summary = "搜素图书")
    public Page<BookViewModel> searchBook(
            @PathVariable String temp,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "14") int size) {
        return pageMapper.pageBookToPageBookViewModel(
                bookAppService.findAllByTemp(temp, PageRequest.of(page, size)));
    }

    @GetMapping("/findBookById/{id}")
    @Tag(name = "book")
    @Operation(summary = "查看图书")
    public BookViewModel findBookById(@PathVariable("id") Integer id) {
        return bookMapper.bookToBookViewModel(
                bookAppService.findById(id));
    }
}
