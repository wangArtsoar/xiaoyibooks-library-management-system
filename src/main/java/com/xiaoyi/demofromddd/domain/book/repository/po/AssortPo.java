package com.xiaoyi.demofromddd.domain.book.repository.po;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description AssortPo
 * @date 2023/5/28 14:07
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssortPo {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer bookNumber;
}
