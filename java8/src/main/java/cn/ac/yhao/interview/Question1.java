package cn.ac.yhao.interview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>功能: 编写程序,在C盘根目录下创建文件myFile.txt，文件内容如下，请注意缩进和换行：
Java
	C/C++
		Python
			JavaScripts
 */
public class Question1 {
	public void answer() {
		String filePath = "C:\\myFile.txt"; //使用的mac电脑，没有在Windows上验证
		String str = "Java\n\tC/C++\n\t\tPython\n\t\t\tJavaScripts";

		FileOutputStream fos = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			byte[] bytes = str.getBytes("utf-8");
			int b = bytes.length;

			fos = new FileOutputStream(file);
			fos.write(bytes, 0, b);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Question1 q = new Question1();
		q.answer();
	}
}
