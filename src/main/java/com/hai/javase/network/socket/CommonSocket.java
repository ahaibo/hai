package com.hai.javase.network.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/9/10.
 */
public class CommonSocket {
    public static final int SERVER_PORT = 10000;
    public static final int SOCKET_TIMEOUT = 10 * 1000;
    public static final String END_OF_STRING = "eof";

    public static String readContent(BufferedReader reader) throws IOException {
        return readContent(reader, 1024);
    }

    public static String readContent(BufferedReader reader, int bufferSize) throws IOException {
        int len;
        String str;
        char[] buffer = new char[bufferSize <= 0 ? 1024 : bufferSize];
        StringBuilder text = new StringBuilder();

        while ((len = reader.read(buffer)) != -1) {
            str = new String(buffer, 0, len);
            text.append(str);
            if (CommonSocket.isEOF(str)) break;
        }

        return CommonSocket.subString(text);
    }

    public static boolean isEOF(String str) {
        return str.trim().toLowerCase().endsWith(END_OF_STRING);
    }

    public static String subString(StringBuilder str) {
        return subString(str.toString());
    }

    public static String subString(String str) {
        return str.substring(0, str.length() - END_OF_STRING.length());
    }

    public static void close(BufferedReader reader) {
        close(null, null, null, reader, null);
    }

    public static void close(Socket socket, BufferedWriter writer) {
        close(socket, null, null, null, writer);
    }

    public static void close(Socket socket, BufferedReader reader, BufferedWriter writer) {
        close(socket, null, null, reader, writer);
    }

    public static void close(Socket socket, InputStream in, OutputStream out, BufferedReader reader, BufferedWriter writer) {
        try {
            if (null != socket) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != in) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (null != out) {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != reader) {
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != writer) {
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
