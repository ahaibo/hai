package com.hai.javase.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListFile {

	public static void main(String[] args) {
		printListFile();
	}

	/**
	 * 列出某文件夹下的所有文件
	 */
	private static void printListFile() {
		String s = "";
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(ir);
		try {
			s = in.readLine();
			File f = new File(s);
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					System.out.println("文件：" + files[i]);
				} else {
					System.out.println("目录：" + files[i]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}