package com.hai.javase.thread.multithread.down;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/12/28.
 */
public class MultDownloadRunnable implements Runnable {

    private String url;
    private String path;
    private int threadId;
    private int startIndex;
    private int endIndex;

    public MultDownloadRunnable(String url, String path, int threadId, int startIndex, int endIndex) {
        this.url = url;
        this.path = path;
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        System.out.println("线程" + threadId + "开始下载");
        try {
            //分段请求网络连接,分段将文件保存到本地.
            URL url = new URL(this.url);

            //加载下载位置的文件
            File downThreadFile = new File(this.path, "downThread_" + threadId + ".dt");
            RandomAccessFile downThreadStream = new RandomAccessFile(downThreadFile, "rwd");

            if (downThreadFile.exists()) {//如果文件存在
                String startIndexStr = downThreadStream.readLine();
                if (null != startIndexStr && "".equals(startIndexStr.trim())) {
                    this.startIndex = Integer.parseInt(startIndexStr) - 1;//设置下载起点
                }
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(MultDownload.HTTP_METHOD_GET);
            connection.setConnectTimeout(MultDownload.HTTP_CONN_TIMEOUT);

            //设置分段下载的头信息。  Range:做分段数据请求用的。格式: Range bytes=0-1024  或者 bytes:0-1024
            connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

            System.out.println("线程_" + threadId + "的下载起点是 " + startIndex + "  下载终点是: " + endIndex);

            if (connection.getResponseCode() == MultDownload.HTTP_STATUS_RANGE_OK) {//200：请求全部资源成功， 206代表部分资源请求成功
                InputStream inputStream = connection.getInputStream();//获取流
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                //获取前面已创建的文件.
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.path, MultDownload.getFileName(url)), "rw");
                randomAccessFile.seek(startIndex);//文件写入的开始位置.

                //将网络流中的文件写入本地
                byte[] buffer = new byte[2048];
                int length = -1;
                int total = 0;//记录本次下载文件的大小
                while ((length = bis.read(buffer)) > 0) {
                    randomAccessFile.write(buffer, 0, length);
                    total += length;
                    //将当前下载到的位置保存到文件中
                    downThreadStream.seek(0);
                    downThreadStream.write((startIndex + total + "").getBytes("UTF-8"));
                }

                downThreadStream.close();
                //                inputStream.close();
                bis.close();
                randomAccessFile.close();
                MultDownload.cleanTemp(downThreadFile);//删除临时文件
                System.out.println("线程" + threadId + "下载完毕");
            } else {
                System.out.println("响应码是" + connection.getResponseCode() + ". 服务器不支持多线程下载");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
