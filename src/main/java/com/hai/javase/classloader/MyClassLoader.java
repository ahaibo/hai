/**
 *
 */
package com.hai.javase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Administrator
 */
public class MyClassLoader extends ClassLoader {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String srcFilePath = "D:\\Users\\Administrator\\Workspaces\\MyEclipse Professional\\test\\build\\classes\\cn\\com\\test\\classloader\\MyClassLoaderAttachment.class";
        String destDirName = "myClassLoaderLib";
        String destFileName = srcFilePath.substring(srcFilePath.lastIndexOf(File.separator) + 1);
        String destPath = destDirName + File.separator + destFileName;

        InputStream ins = null;
        OutputStream ots = null;
        try {
            ins = new FileInputStream(srcFilePath);
            ots = new FileOutputStream(destPath);
            cipher(ins, ots);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != ins) {
                try {
                    ins.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (null != ots) {
                try {
                    ots.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 简单加密，对数据亦或【取反】操作，次简单操作既可加密亦可解密
     */
    private static void cipher(InputStream ins, OutputStream ots) {
        // TODO Auto-generated method stub
        int b = -1;
        try {
            while ((b = ins.read()) != -1) {

                ots.write(b ^ 0xff);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String directory;

    /*
     * (non-Javadoc)
     *
     * @see java.lang.ClassLoader#findClass(java.lang.String)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = this.directory.concat(File.separator).concat(name).concat(".class");
        // 读取类字节
        InputStream is = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            is = new FileInputStream(filePath);
            byteArrayOutputStream = new ByteArrayOutputStream();
            cipher(is, byteArrayOutputStream);
            System.out.println("my findClass..");
            byte[] fileByteArray = byteArrayOutputStream.toByteArray();
            return defineClass(name, fileByteArray, 0, fileByteArray.length);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return super.findClass(name);
    }

    /**
     *
     */
    public MyClassLoader() {
    }

    /**
     * @param parent
     */
    public MyClassLoader(ClassLoader parent) {
    }

    public MyClassLoader(String directory) {
        this.setDirectory(directory);
    }

    /**
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
