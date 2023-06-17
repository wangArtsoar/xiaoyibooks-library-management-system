package com.xiaoyi.librarymanagementsystem.domain.user.repository.persistence;

import com.xiaoyi.librarymanagementsystem.domain.user.repository.po.TokenPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author 王艺翔
 * @description TokenRepository
 * @date 2023/6/17 12:01
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public interface TokenRepository extends JpaRepository<TokenPo, Integer> {

	@Query(value = """
					     SELECT t FROM TokenPo t INNER JOIN UserPo u \s
					     ON t.userPo.email = u.email \s
					     WHERE u.email = :userEmail AND t.invalid = false
					""")
	Optional<TokenPo> findByUserEmail(@Param("userEmail") String userEmail);

	Optional<TokenPo> findByToken(String token);
}
