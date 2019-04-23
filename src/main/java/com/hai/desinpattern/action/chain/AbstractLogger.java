package com.hai.desinpattern.action.chain;

public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

//    public void logMessage(int level, String message) {
//        if (this.level <= level) {
//            write(message);
//        }
//        if (nextLogger != null) {
//            nextLogger.logMessage(level, message);
//        }
//    }

    /**
     * 责任链模式核心处理 20190423 06:33:17
     *
     * @param message
     */
    public void chain(String message) {
        write(message);
        if (null != this.nextLogger) {
            this.nextLogger.chain(message);
        }
    }

    abstract protected void write(String message);

}