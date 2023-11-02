package com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence;

import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 王艺翔
 * @description BookRepository
 * @date 2023/5/29 18:28
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface BookRepository extends JpaRepository<BookPo, Integer> {

	@Query(value = """ 
					  SELECT count(*) FROM BookPo b group by b.assortName
					""")
	Integer getCountGroupAssortName();

//	@Query("""
//					SELECT a.id as assortId,assortName as assortName,count(*) as count FROM BookPo b\s
//					inner join AssortPo a on b.assortName = a.name \s
//					group by assortName,a.id
//					""")
//	List<AssortCount> countGroupByAssortName(List<String> assortNameList);

	@NotNull Page<BookPo> findAll(@NotNull Pageable pageable);

	Page<BookPo> findByAssortName(Pageable pageable, String assortName);


}
