package com.xiaoyi.librarymanagementsystem.infrastructure.common.mappers;

import com.xiaoyi.librarymanagementsystem.application.dto.BookDto;
import com.xiaoyi.librarymanagementsystem.application.dto.viewmodel.BookViewModel;
import com.xiaoyi.librarymanagementsystem.domain.book.entity.Book;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description BookAssembler
 * @date 2023/5/29 20:58
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper
public interface BookMapper {

	@Mapping(target = "filePath", expression = "java(map(bookDto.file()))")
	Book bookDtoToBook(BookDto bookDto);

	default String map(MultipartFile file) {
		// 在这里实现您的自定义转换逻辑
		// 例如，您可以将文件上传到服务器并返回文件的 URL
		return file.getOriginalFilename();
	}

	List<Book> bookDtoToBookList(List<BookDto> bookDtos);

	BookViewModel bookToBookViewModel(Book book);

	default Map<String, List<BookViewModel>> bookToBookListViewModelMap(List<Book> books) {
		return books
						.stream()
						.map(this::bookToBookViewModel)
						.collect(Collectors.groupingBy(BookViewModel::assortName));
	}

	List<BookPo> bookToBookPoList(List<Book> books);

	BookPo bookToBookPo(Book book);

	List<Book> bookPoToBookList(List<BookPo> bookPos);

	Book bookPoToBook(BookPo bookPo);

}
