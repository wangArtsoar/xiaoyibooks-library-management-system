package com.xiaoyi.librarymanagementsystem.application.dto.viewmodel;

import java.util.List;
import java.util.Map;

/**
 * @author 王艺翔
 * @description UserDetailViewModel
 * @date 2023/5/28 13:52
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public record UserDetailViewModel(String name, String email,
                                  Map<String, List<BorrowSimpleVo>> borrowSimpleVoMap) {
}
