package com.xiaoyi.librarymanagementsystem.domain.book.service;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Assort;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.AssortCount;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.AssortRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence.BookRepository;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import com.xiaoyi.librarymanagementsystem.infrastructure.exception.CreateFailException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final PlatformTransactionManager transactionManager;

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
		assortNameList.forEach(assortName -> assorts.add(updateAssort(null, assortName, 0)));
		List<AssortPo> assortPos = BookFactory.toAssortPoList(assorts);
		List<AssortPo> assortAlreadyExist = assortRepository.findByNameIn(
						assortPos.stream().map(AssortPo::getName).collect(Collectors.toList()));
		if (assortAlreadyExist.size() > 0) {
			List<String> alreadyName = assortAlreadyExist.stream().map(AssortPo::getName).toList();
			assortPos.removeIf(assortPo -> alreadyName.contains(assortPo.getName()));
		}
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			if (assortPos.size() > 0) {
				assortPos = assortRepository.saveAllAndFlush(assortPos);
				logger.info("图书类型添加成功: {}", assortPos);
			}
			List<BookPo> bookPos = BookFactory.toBookPoList(
							map.values().stream().flatMap(List::stream).toList());
			List<Book> BooksResponse = BookFactory.toBookList(bookRepository.saveAllAndFlush(bookPos));
			logger.info("图书添加成功: {}", bookPos);
			List<AssortCount> assortCounts = bookRepository.countGroupByAssortName(assortNameList);
			List<AssortPo> assortPoList = BookFactory.toAssortPoList(assortCounts.stream()
							.map(assortCount -> updateAssort(assortCount.getAssortId(), assortCount.getAssortName(),
											assortCount.getCount()))
							.collect(Collectors.toList()));
			assortRepository.saveAll(assortPoList);
			logger.info("图书类型修改成功: {}", assortPoList);
			transactionManager.commit(status);
			return BooksResponse;
		} catch (Exception e) {
			logger.error("Failed to add books", e);
			transactionManager.rollback(status);
			throw new CreateFailException("添加失败" + e);
		}
	}

	private Assort updateAssort(Integer id, String assortName, int amount) {
		return Assort.builder()
						.id(id)
						.name(assortName)
						.address(assortName + "区")
						.bookAmount(amount)
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
