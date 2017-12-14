package com.hai.ijavase.igui.multcopy.v1;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2017/9/22.
 */
public class Copier {
    private CopyWindow copyWindow;
    private int threadCount;
    private String srcFile;
    private String destFile;

    public Copier(CopyWindow mainUI, int threadCount, String srcFile, String destFile) {
        this.copyWindow = mainUI;
        this.threadCount = threadCount;
        this.srcFile = srcFile;
        this.destFile = destFile;
        this.startCopy();
    }

    private void startCopy() {
        int start = 0;
        int end = 0;

        try {
//            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            File destFile = new File(this.destFile);
            if (destFile.exists()) {
                destFile.delete();
                System.out.println("delete exists file: " + this.destFile);
            }
            RandomAccessFile raf = new RandomAccessFile(this.srcFile, "r");
            int length = (int) raf.length();
            int block = length / this.threadCount;
            this.copyWindow.setProgressBarMaxValue(length);

            for (int i = 0; i < threadCount; i++) {
                if (i != threadCount - 1) {
                    end = (i + 1) * block - 1;
                } else {
                    end = length - 1;
                }
                CopyInfo copyInfo = CopyInfo.newInstance(this.srcFile, this.destFile, start, end);
                new CopyThread(this.copyWindow, copyInfo).start();
//                executorService.submit(new CopyThread(this.copyWindow, copyInfo));
            }
//            executorService.shutdown();
//            while (true) {
//                if (executorService.isTerminated()) {
//                    System.out.println("multi copy completed...");
//                    this.copyWindow.visibleCompleteLabel();
//                    break;
//                }
////                Thread.sleep(2000);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
