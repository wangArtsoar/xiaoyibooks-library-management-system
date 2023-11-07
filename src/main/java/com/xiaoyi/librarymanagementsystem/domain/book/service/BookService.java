package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.Borrow;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 王艺翔
 * @description BookService
 * @date 2023/5/29 21:01
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface BookService {
    Book addBook(Book book);

    Page<Book> findAllByPageable(Pageable pageable);

    Page<Book> findByAssortName(Pageable pageable, String assortName);

    Book editBook(Integer id, Book book);

    Borrow borrowBook(String email, Integer bookId);

    Page<Book> findAllByTemp(String temp, Pageable pageable);

    List<Borrow> getBorrowByUser(User user);

    Book findById(Integer id);
}
