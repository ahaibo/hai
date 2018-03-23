package com.hai.javase.thread.multithread.copy.ui;

/**
 * Created by Administrator on 2018/2/26.
 */
public class CopyInfo {
    private String src;
    private String dest;
    private int start;
    private int end;

    public CopyInfo() {
    }

    public CopyInfo(String src, String dest, int start, int end) {
        this.src = src;
        this.dest = dest;
        this.start = start;
        this.end = end;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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
