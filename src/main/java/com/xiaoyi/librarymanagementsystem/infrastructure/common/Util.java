package com.xiaoyi.librarymanagementsystem.infrastructure.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 王艺翔
 * @description Util
 * @date 2023/6/7 17:02
 * @phone 18318436514
 * @email xiaoyi_wyx@icloud.com
 * @github <a href="https://github.com/Tom-Collection>...</a>
 */
public class Util {
	public static void saveFileToServer(MultipartFile file) {
		// 指定文件保存的位置
		String savePath = "C:\\Users\\26034\\Downloads\\";
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 创建文件对象
		assert fileName!=null;
		File saveFile = new File(savePath, UUID.randomUUID() + fileName);
		try {
			// 将文件保存到服务器上的指定位置
			file.transferTo(saveFile);
			// 返回文件的路径
			System.out.println(saveFile.getAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException("保存文件失败" + e);
		}
	}
}
