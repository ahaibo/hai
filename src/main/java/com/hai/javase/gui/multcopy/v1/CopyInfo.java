package com.hai.javase.gui.multcopy.v1;

/**
 * Created by Administrator on 2017/9/22.
 */
public class CopyInfo {
    private int index;
    private int start;
    private int end;
    private String srcFile;
    private String destFile;

    public CopyInfo() {
    }

    public CopyInfo(String srcFile, String destFile, int start, int end) {
        this.srcFile = srcFile;
        this.destFile = destFile;
        this.start = start;
        this.end = end;
    }

    public static CopyInfo newInstance(String srcFile, String destFile, int start, int end) {
        return new CopyInfo(srcFile, destFile, start, end);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String destFile) {
        this.destFile = destFile;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
