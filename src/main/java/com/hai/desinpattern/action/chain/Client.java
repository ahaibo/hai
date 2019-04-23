package com.hai.desinpattern.action.chain;

/*
一、模式的优缺点
优点
1、降低耦合度。它将请求的发送者和接受者解耦。
2、简化了对象。使得对象不需要知道链的结构。
3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
4、增加新的请求处理类很方便。

缺点
1、不能保证请求一定被接收。
2、系统性能将受到一定影响，而且在进行代码调试时不太方便；可能会造成循环调用。
3、可能不容易观察运行时的特征，有碍于除错。


二、模式适用场景
1、有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。
2、在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。
3、可动态指定一组对象处理请求。


三、总结
1、职责链模式将请求的发送者和接受者解耦了。客户端不需要知道请求处理者的明确信息，甚至不需要知道链的结构，它只需要将请求进行发送即可。
2、职责链模式能够非常方便的动态增加新职责或者删除职责。
3、客户端发送的请求可能会得不到处理。
4、处理者不需要知道链的结构，只需要明白他的后续者是谁就可以了。这样就简化了系统中的对象。
*/

public class Client {

    private static AbstractLogger getChainOfLoggers() {

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        consoleLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(errorLogger);

        return consoleLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.chain("This is an information.");
//        loggerChain.logMessage(AbstractLogger.DEBUG, "This is an debug level information.");
//        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}