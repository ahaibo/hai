package com.hai.ijavase.igui.imsg.utils;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */
public class CommonUtils {

    public static byte[] inttoBytes(int i) {
        byte[] arr = new byte[4];
        arr[0] = (byte) (i >> 24);
        arr[1] = (byte) (i >> 16);
        arr[2] = (byte) (i >> 8);
        arr[3] = (byte) i;
        return arr;
    }

    public static int bytesToInt(byte[] arr) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        int i3 = (arr[0] & 0xFF) << 24;
        int i2 = (arr[1] & 0xFF) << 16;
        int i1 = (arr[2] & 0xFF) << 8;
        int i = arr[3] & 0xFF;
        return i3 | i2 | i1 | i;
    }

    public static int readMessageType(InputStream is) throws IOException {
        return is.read();
    }

    public static int readMessageLength(InputStream is) throws IOException {
        byte[] arr = new byte[4];
        is.read(arr);
        return bytesToInt(arr);
    }

    public static byte[] readMessageContent(InputStream is, int length) throws IOException {
        byte[] buffer = new byte[length];
        is.read(buffer);
        return buffer;
    }

    /**
     * 序列化好友信息为流
     *
     * @param socketIps
     * @return
     */
    public static byte[] serializebleFriends(List<String> socketIps) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(socketIps);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 反序列化好友信息
     *
     * @param bytes
     * @return
     */
    public static List<String> serializebleFriends(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(bais);
            return (List<String>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
