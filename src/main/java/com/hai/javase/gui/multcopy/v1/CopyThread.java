package com.hai.javase.gui.multcopy.v1;

import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2017/9/22.
 */
public class CopyThread extends Thread {

    private CopyWindow copyWindow;
    private CopyInfo copyInfo;

    public CopyThread(CopyWindow mainUI, CopyInfo copyInfo) {
        this.copyWindow = mainUI;
        this.copyInfo = copyInfo;
    }

    @Override
    public void run() {
        try {
            RandomAccessFile src = new RandomAccessFile(this.copyInfo.getSrcFile(), "r");
            RandomAccessFile dest = new RandomAccessFile(this.copyInfo.getDestFile(), "rw");

            src.seek(this.copyInfo.getStart());
            dest.seek(this.copyInfo.getStart());

            //缓存
            byte[] buffer = new byte[1024];

            //计算复制量
            int amount = this.copyInfo.getEnd() - this.copyInfo.getStart() + 1;

            //计算复制次数
            int remain = amount % buffer.length;
            int loops = amount / buffer.length;
            loops = remain == 0 ? loops : loops + 1;

            for (int i = 0, len = buffer.length; i < loops; i++) {
                if (i == loops - 1) {
                    //最后一次循环是否是remain != 0时多出来的一次循环，是则len=remain，否则len=buffer.length
                    len = remain == 0 ? len : remain;
                    if (len != buffer.length) {
                        buffer = new byte[len];
                    }
                }
                src.read(buffer);
                dest.write(buffer);
                this.copyWindow.updateProgressBar(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
