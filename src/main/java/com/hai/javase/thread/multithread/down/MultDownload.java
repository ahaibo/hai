package com.hai.javase.thread.multithread.down;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 多线程下载
 * Created by Administrator on 2017/12/28.
 */
public class MultDownload {

    public static String HTTP_METHOD_POST = "POST";
    public static String HTTP_METHOD_GET = "GET";
    public static int HTTP_STATUS_OK = 200;
    public static int HTTP_STATUS_RANGE_OK = 206;
    public static int HTTP_CONN_TIMEOUT = 10000;
    private String url = "https://dl.google.com/android/repository/sdk-tools-windows-3859397.zip?utm_source=androiddevtools.cn&utm_medium=website";
    private String path = "D:\\Data\\test\\multdown";
    private int threadCount = 5;

    public static void main(String[] args) {
        try {
            new MultDownload().download();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MultDownload() {
    }

    public MultDownload(String url, String path, int threadCount) {
        this.url = url;
        this.path = path;
        this.threadCount = threadCount;
    }

    public void download() throws Exception {
        //连接资源
        URL url = new URL(this.url);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HTTP_METHOD_GET);
        connection.setConnectTimeout(HTTP_CONN_TIMEOUT);

        int code = connection.getResponseCode();
        if (code == HTTP_STATUS_OK) {
            //获取资源大小
            int contentLength = connection.getContentLength();
            System.out.println("contentLength: " + contentLength);

            //在本地创建一个与资源同样大小的文件来占位
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(path, getFileName(url)), "rw");
            randomAccessFile.setLength(contentLength);

            //将下载任务分配给每个线程
            int blockSize = contentLength / threadCount;//计算每个线程理论上下载的数量.

            for (int threadId = 0; threadId < threadCount; threadId++) {//为每个线程分配任务
                int startIndex = threadId * blockSize; //线程开始下载的位置
                int endIndex = (threadId + 1) * blockSize - 1; //线程结束下载的位置
                if (threadId == (threadCount - 1)) {  //如果是最后一个线程,将剩下的文件全部交给这个线程完成
                    endIndex = contentLength - 1;
                }
                //开启线程
                new Thread(new MultDownloadRunnable(this.url, this.path, threadId, startIndex, endIndex)).start();//开启线程下载
            }
            //randomAccessFile.close();
        }
    }

    public static String getFileName(URL url) {
        String filename = url.getFile();
        //        return filename.substring(filename.lastIndexOf("/") + 1);
        return "mult-down.zip";
    }

    //删除线程产生的临时文件
    public static synchronized void cleanTemp(File file) {
        file.delete();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
