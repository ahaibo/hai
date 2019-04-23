package com.hai.desinpattern.action.chain;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        if (this.level == AbstractLogger.DEBUG) {
            System.out.println("File::Logger: " + message);
        }
    }
}