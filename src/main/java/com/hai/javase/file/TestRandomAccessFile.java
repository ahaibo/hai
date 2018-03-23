/**
 * 
 */
package com.hai.javase.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * @author as
 *
 */
public class TestRandomAccessFile {

	@Test
	public void test1() {
		RandomAccessFile raf1 = null;
		RandomAccessFile raf2 = null;
		try {
			File f1 = new File("file/file1.txt");
			File f2 = new File("file/file2.txt");
			if (!f1.exists())
				f1.mkdirs();
			if (!f2.exists())
				f2.createNewFile();
			raf1 = new RandomAccessFile(f1, "r");
			raf2 = new RandomAccessFile(f2, "rw");

			String str = null;
			while ((str = raf1.readLine()) != null) {
				System.out.println(str);
				// raf2.writeUTF(str);
				// raf2.writeChars(str);
				raf2.writeBytes(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf2 != null)
					raf2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != raf1)
					raf1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void seek() {
		RandomAccessFile raf = null;
		int pointer = 4;
		try {
			raf = new RandomAccessFile("file/file1.txt", "rw");
			raf.seek(pointer);
			String str = raf.readLine();
			System.out.println(str);
			long p = raf.getFilePointer();
			System.out.println(p);
			// FileChannel channel = raf.getChannel();
			raf.seek(pointer);
			raf.writeBytes("123");
			raf.writeBytes(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != raf) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
