package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.AssortRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.BookRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.Borrow;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.User;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.BorrowRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.BorrowPo;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.BookMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.BorrowMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.PageMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers.UserMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.exception.CreateFailException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;

/**
 * @author 王艺翔
 * @description BookServiceImpl
 * @date 2023/5/29 21:02
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;
    private final PageMapper pageMapper;
    private final BorrowMapper borrowMapper;
    private final UserMapper userMapper;
    private final AssortRepository assortRepository;
    private final PlatformTransactionManager transactionManager;

    @Override
    public Book addBook(Book book) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            if (assortRepository.findByName(book.getAssortName()) == null) {
                assortRepository.saveAndFlush(AssortPo.builder().name(book.getAssortName()).build());
            }
            Book bookResponse = bookMapper.bookPoToBook(bookRepository.save(updateBookPo(book)));
            transactionManager.commit(status);
            return bookResponse;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new CreateFailException("添加图书失败: " + e);
        }
    }

    private BookPo updateBookPo(Book book) {
        BookPo bookPo = bookMapper.bookToBookPo(book);
        bookPo.setCreateAt(new Date());
        String address = getBookAddress(book.getAssortName());
        bookPo.setAddress(bookPo.getAssortName() + address);
        return bookPo;
    }

    private String getBookAddress(String AssortCount) {
        int next;
        Integer i = bookRepository.getCountByAssortName(AssortCount);
        if (i == null) {
            next = 1;
        } else {
            next = i + 1;
        }
        return next < 100 ? String.format("%03d", next) : String.valueOf(next);
    }

    @Override
    public Page<Book> findAllByPageable(Pageable pageable) {
        return pageMapper.pageBookPoToPageBook(bookRepository.findAll(pageable));
    }

    @Override
    public Page<Book> findByAssortName(Pageable pageable, String assortName) {
        return pageMapper.pageBookPoToPageBook(bookRepository.findByAssortName(pageable, assortName));
    }

    @Override
    public Book editBook(Integer id, Book book) {
        BookPo bookPo = bookRepository.findById(id).orElseThrow();
        bookPo.setName(book.getName());
        bookPo.setAssortName(book.getAssortName());
        bookPo.setAuthor(book.getAuthor());
        bookPo.setPublishDate(book.getPublishDate());
        return bookMapper.bookPoToBook(bookRepository.save(bookPo));
    }

    @Override
    public Borrow borrowBook(String email, Integer bookId) {
        BookPo bookPo = bookRepository.findById(bookId).orElseThrow();
        UserPo userPo = userRepository.findByEmail(email).orElseThrow();
        var borrowPo = BorrowPo.builder()
                .bookPo(bookPo)
                .userPo(userPo)
                .borrowDate(new Date())
                .build();
        return borrowMapper.borrowPoToBorrow(borrowRepository.save(borrowPo));
    }

    @Override
    public Page<Book> findAllByTemp(String temp, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", GenericPropertyMatcher::contains)
                .withMatcher("author", GenericPropertyMatcher::contains)
                .withMatcher("assortName", GenericPropertyMatcher::contains);
        BookPo bookPo = BookPo.builder().name(temp).author(temp).assortName(temp).build();
        Example<BookPo> example = Example.of(bookPo, matcher);
        Page<BookPo> all = bookRepository.findAll(example, pageable);
        return pageMapper.pageBookPoToPageBook(all);
    }

    @Override
    public List<Borrow> getBorrowByUser(User user) {
        return borrowMapper.borrowPoToBorrowList(borrowRepository.findAllByUserPo(userMapper.userToUserPo(user)));
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.bookPoToBook(bookRepository.findById(id).orElse(null));
    }
}
