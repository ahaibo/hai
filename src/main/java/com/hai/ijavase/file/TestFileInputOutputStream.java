/**
 *
 */
package com.hai.ijavase.file;

import org.junit.Test;

import java.io.*;

/**
 * @author as
 */
public class TestFileInputOutputStream {

    @Test
    public void normalReadWrite() {
        String path = "D:\\Data\\test\\a.png";
    }

    @Test
    public void readImg2Txt() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\Data\\test\\a.png"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\Data\\test\\a.txt"));

        int b;
        while ((b = bis.read()) != -1) {
            bos.write((b + "").getBytes());
            bos.write(new byte[]{'\r', '\n'});
        }

        bos.close();
        bis.close();
    }

    @Test
    /**
     * readImg2Txt 与 readImg2Txt2 最主要的区别在于：
     * readImg2Txt 存储的是 bis.read() 的返回值 [0 ~ 255]
     * readImg2Txt2 存储的是 bis.read() 真正读取到的byte值 [ -128 ~ 127]
     */
    public void readImg2Txt2() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\Data\\test\\a.png"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Data\\test\\a-2.txt"));

        int len;
        byte[] buffer = new byte[1024];
        while ((len = bis.read(buffer)) != -1) {
            for (int i = 0; i < len; i++) {
                writer.write(buffer[i] + "\r\n");
            }
        }

        writer.flush();
        bis.close();
        writer.close();
    }

    @Test
    public void readTxt2Img() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\test\\a-2.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\Data\\test\\a-2.png"));

        String s;
        while ((s = reader.readLine()) != null) {
            byte b = (byte) Integer.parseInt(s);
            bos.write(b);
        }

        bos.close();
        reader.close();
    }

}
