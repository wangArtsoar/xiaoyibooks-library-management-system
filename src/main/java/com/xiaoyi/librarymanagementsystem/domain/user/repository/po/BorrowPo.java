package com.xiaoyi.librarymanagementsystem.domain.user.repository.po;

import com.xiaoyi.librarymanagementsystem.domain.book.repository.po.BookPo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 王艺翔
 * @description BorrowPo
 * @date 2023/5/29 20:45
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "borrow")
public class BorrowPo {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "user_email", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private UserPo userPo;
	@OneToOne
	@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private BookPo bookPo;
	private Date borrowDate;
	private Date escheatDate;
}