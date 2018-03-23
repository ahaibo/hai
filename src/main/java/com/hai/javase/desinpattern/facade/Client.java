package com.hai.javase.desinpattern.facade;

/**
 * 所谓外观模式就是提供一个统一的接口，用来访问子系统中的一群接口。
 * 外观模式定义了一个高层接口，让子系统更容易使用。
 * <p>
 * 模式优缺点
 * <p>
 * 优点
 * 1、引入外观模式，是客户对子系统的使用变得简单了，减少了与子系统的关联对象，实现了子系统与客户之间的松耦合关系。
 * 2、只是提供了一个访问子系统的统一入口，并不影响用户直接使用子系统类
 * 3、降低了大型软件系统中的编译依赖性，并简化了系统在不同平台之间的移植过程
 * <p>
 * 缺点
 * 1、不能很好地限制客户使用子系统类，如果对客户访问子系统类做太多的限制则减少了可变性和灵活性
 * 2、在不引入抽象外观类的情况下，增加新的子系统可能需要修改外观类或客户端的源代码，违背了“开闭原则”
 * <p>
 * 使用场景
 * <p>
 * 1、当要为一个复杂子系统提供一个简单接口时可以使用外观模式。
 * 2、客户程序与多个子系统之间存在很大的依赖性。引入外观类将子系统与客户以及其他子系统解耦，可以提高子系统的独立性和可移植性
 * Created by Administrator on 2018/2/5.
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.setSubSystem1(new SubSystem1());
        facade.setSubSystem2(new SubSystem2());
        facade.setSubSystem3(new SubSystem3());

        facade.on();
        facade.off();
    }
}
