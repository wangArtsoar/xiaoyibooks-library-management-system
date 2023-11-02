package com.xiaoyi.librarymanagementsystem.domain.user.repository.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description TokenPo
 * @date 2023/6/17 11:55
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "token")
public class TokenPo {
	@Id
	@GeneratedValue
	private Integer id;
	private String token;
	@ManyToOne
	@JoinColumn(name = "user_email", referencedColumnName = "email",
					foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private UserPo userPo;
	@Column(columnDefinition = "boolean default false")
	private boolean invalid;
}
