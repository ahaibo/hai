/**
 *
 */
package com.hai.javase.file.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author Administrator
 */
public class SerializationUtils {

    private static String FILE_NAME = "D:/Data/test/obj.serialize";

    public static void writeObject(Serializable serializable, String filepath) {

        if (null != filepath && filepath.length() > 0) {
            FILE_NAME = filepath;
        }
        writeObject(serializable);
    }

    public static void writeObject(Serializable serializable) {

        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new FileOutputStream(FILE_NAME);
            oos = new ObjectOutputStream(os);

            oos.writeObject(serializable);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(null, null, os, oos);
        }
    }

    public static Object readObject(String filepath) {

        if (null != filepath && filepath.length() > 0) {
            FILE_NAME = filepath;
        }

        return readObject();
    }

    public static Object readObject() {

        ObjectInputStream ois = null;
        InputStream in = null;
        Object object = null;

        try {
            in = new FileInputStream(FILE_NAME);
            ois = new ObjectInputStream(in);
            object = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(in, ois, null, null);
        }

        return object;

    }

    public static void close(InputStream in, ObjectInputStream ois, OutputStream os, ObjectOutputStream oos) {

        if (null != oos) {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (null != ois) {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
