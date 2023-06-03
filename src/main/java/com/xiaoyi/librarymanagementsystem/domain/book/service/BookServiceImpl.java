package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Assort;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.AssortRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.BookRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import com.xiaoyi.librarymanagementsystem.domain.user.entity.Borrow;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.BorrowRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence.UserRepository;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.BorrowPo;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.AssortMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.BookMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.BorrowMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.common.util.PageMapper;
import com.xiaoyi.librarymanagementsystem.infrastructure.exception.CreateFailException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
	private final AssortRepository assortRepository;
	private final BorrowRepository borrowRepository;
	private final UserRepository userRepository;
	private final PlatformTransactionManager transactionManager;
	private final BookMapper bookMapper;
	private final AssortMapper assortMapper;
	private final PageMapper pageMapper;
	private final BorrowMapper borrowMapper;

	@Override
	public List<Book> addBookList(List<Book> books) {
		Map<String, List<Book>> map = books.stream().collect(Collectors.groupingBy(Book::getAssortName));
		return addBookMap(map);
	}

	@Override
	public List<Book> addBookMap(Map<String, List<Book>> map) {
		map.forEach((k, v) -> map.put(k, updateAttributes(k, v)));
		List<Assort> assorts = new ArrayList<>();
		List<String> assortNameList = map.keySet().stream().toList();
		assortNameList.forEach(assortName -> assorts.add(updateAssort(assortName)));
		List<AssortPo> assortPos = assortMapper.assortToAssortPoList(assorts);
		List<AssortPo> assortAlreadyExist = assortRepository.findByNameIn(
						assortPos.parallelStream().map(AssortPo::getName).collect(Collectors.toList()));
		if (assortAlreadyExist.size() > 0) {
			List<String> alreadyName = assortAlreadyExist.parallelStream().map(AssortPo::getName).toList();
			assortPos.removeIf(assortPo -> alreadyName.contains(assortPo.getName()));
		}
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			if (assortPos.size() > 0) {
				assortPos = assortRepository.saveAll(assortPos);
				logger.info("图书类型添加成功: {}", assortPos);
			}
			List<BookPo> bookPos = bookMapper.bookToBookPoList(map.values().stream().flatMap(List::stream).toList());
			List<Book> BooksResponse = bookMapper.bookPoToBookList(bookRepository.saveAll(bookPos));
			logger.info("图书添加成功: {}", bookPos);
			transactionManager.commit(status);
			return BooksResponse;
		} catch (Exception e) {
			logger.error("Failed to add books", e);
			transactionManager.rollback(status);
			throw new CreateFailException("添加失败" + e);
		}
	}

	@Override
	public Page<Book> findAllByPageable(Pageable pageable) {
		return pageMapper.pageBookPoToPageBook(bookRepository.findAll(pageable));
	}

	@Override
	public Page<Book> findbyAssortName(Pageable pageable, String assortName) {
		return pageMapper.pageBookPoToPageBook(bookRepository.findByAssortName(pageable, assortName));
	}

	@Override
	public Book editBook(Integer id, Book book) {
		BookPo bookPo = bookRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(book, bookPo);
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
		BookPo bookPo = BookPo
						.builder()
						.name(temp)
						.author(temp)
						.assortName(temp)
						.build();
		Example<BookPo> example = Example.of(bookPo);
		return pageMapper.pageBookPoToPageBook(bookRepository.findAll(example, pageable));
	}

	private Assort updateAssort(String assortName) {
		return Assort.builder()
						.name(assortName)
						.address(assortName + "区")
						.build();
	}

	private List<Book> updateAttributes(String assortName, List<Book> bookList) {
		int currId = bookRepository.getNextId() - 1;
		AtomicInteger idCounter = new AtomicInteger(currId);
		return bookList.stream().map(book -> updateBook(assortName, idCounter.incrementAndGet(), book)).toList();
	}

	private Book updateBook(String assortName, Integer nextId, Book book) {
		return Book.builder()
						.name(book.getName())
						.assortName(assortName)
						.publishDate(book.getPublishDate())
						.author(book.getAuthor())
						.address(assortName + String.format("%03d", nextId))
						.createAt(new Date())
						.build();
	}
}
