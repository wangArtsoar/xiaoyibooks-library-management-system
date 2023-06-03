package com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence;

import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.BorrowPo;
import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 王艺翔
 * @description BorrowRepository
 * @date 2023/5/29 18:57
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface BorrowRepository extends JpaRepository<BorrowPo, Integer> {
	List<BorrowPo> findAllByUserPo(UserPo user);
}
