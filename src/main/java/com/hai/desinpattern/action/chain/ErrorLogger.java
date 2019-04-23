package com.hai.desinpattern.action.chain;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        if (this.level == AbstractLogger.ERROR) {
            System.out.println("Error Console::Logger: " + message);
        }
    }
}