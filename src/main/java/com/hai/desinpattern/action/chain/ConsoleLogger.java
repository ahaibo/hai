package com.hai.desinpattern.action.chain;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        if (this.level == AbstractLogger.INFO) {
            System.out.println("Standard Console::Logger: " + message);
        }
    }
}