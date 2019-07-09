package com.hai.javase.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final char[] hexadecimal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MD5Encoder() {
    }

    public static String encode(String s) {
        return encode(s, DEFAULT_CHARSET);
    }

    public static String encode(String s, String charset) {

        if (s == null) {
            return null;
        }

        byte[] strTemp;
        try {
            strTemp = s.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            return null;
        }
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        mdTemp.update(strTemp);
        byte[] binaryData = mdTemp.digest();

        if (binaryData.length != 16) {
            return null;
        }

        char[] buffer = new char[32];

        for (int i = 0; i < 16; i++) {
            int low = binaryData[i] & 0x0f;
            int high = (binaryData[i] & 0xf0) >> 4;
            buffer[i * 2] = hexadecimal[high];
            buffer[i * 2 + 1] = hexadecimal[low];
        }

        return new String(buffer);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            String hash1 = encode(new String(readImageStreams("C:\\tmp\\cache.jpg")));
            String hash2 = encode(new String(readImageStreams("C:\\tmp\\img3.jpeg")));
//            System.out.println("hash1:" + hash1 + "\nhash2:" + hash2);
//            System.out.println("hash1.equals(hash2):" + hash1.equals(hash2));
        }
        long end = System.currentTimeMillis();
        System.out.println("used times:" + (end - start) / 1000 + " s");
    }

    private static byte[] readImageStreams(String path) {
        try (BufferedInputStream ois = new BufferedInputStream(new FileInputStream(path))) {
            byte[] buffer = new byte[ois.available()];
            ois.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}