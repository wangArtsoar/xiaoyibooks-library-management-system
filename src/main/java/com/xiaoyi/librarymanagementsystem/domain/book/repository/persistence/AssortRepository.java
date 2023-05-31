package com.xiaoyi.librarymanagementsystem.domain.book.repository.persistence;

import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 王艺翔
 * @description AssortRepository
 * @date 2023/5/29 18:29
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface AssortRepository extends JpaRepository<AssortPo, Integer> {
	boolean existsByName(String name);

	AssortPo findByName(String name);

	List<AssortPo> findByNameIn(List<String> nameList);
}
