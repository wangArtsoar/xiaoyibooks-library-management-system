package com.xiaoyi.librarymanagementsystem.infrastructure.common.util;

import com.xiaoyi.librarymanagementsystem.domain.book.entity.Assort;
import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.AssortPo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 王艺翔
 * @description AssortMapper
 * @date 2023/6/3 11:39
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Mapper
public interface AssortMapper {
	List<AssortPo> assortToAssortPoList(List<Assort> assorts);

	AssortPo assortToAssortPo(Assort assort);

	Assort toAssort(AssortPo assortPo);
}
