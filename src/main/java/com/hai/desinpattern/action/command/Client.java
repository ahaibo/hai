package com.hai.desinpattern.action.command;

/*
    总结

    1. 命令模式的本质就是将命令对象进行封装打包，将发出命令的责任和执行命令的责任进行割开。
    2. 命令模式中发送者只需要知道如何发送请求命令，无须关心命令执行具体过程。
    3. 在发送者和接收者两者间是通过命令对象进行沟通的。请求命令本身就当做一个对象在两者间进行传递，它封装了接收者和一组动作。
    4. 命令模式支持撤销。
    5. 命令模式队列请求和日志请求。

 * Created by Administrator on 2018/2/8.
 */
public class Client {
    public static void main(String[] args) {
        Television television = new Television();

        OpenTVCommand openTVCommand = new OpenTVCommand(television);
        ChangeChannelTVCommand changeChannelTVCommand = new ChangeChannelTVCommand(television);
        CloseTVCommand closeTVCommand = new CloseTVCommand(television);

        Controller controller = new Controller(openTVCommand, changeChannelTVCommand, closeTVCommand);
        controller.open();
        controller.changeChannel();
        controller.close();

        //方式二
        controller.addCommand(openTVCommand);
        controller.addCommand(changeChannelTVCommand);
        controller.addCommand(closeTVCommand);
        controller.executes();
    }
}
