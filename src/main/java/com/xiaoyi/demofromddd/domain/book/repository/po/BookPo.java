package com.xiaoyi.demofromddd.domain.book.repository.po;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 王艺翔
 * @description BookPo
 * @date 2023/5/28 14:00
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPo {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Date publishDate;
	private Date createAt;
	private Date updateAt;
	private String address;
	@ManyToOne
	private AssortPo assort;
	private Boolean isBorrow;
	private Boolean isDelete;
}
