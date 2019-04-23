package com.hai.desinpattern.action.mediator;

/**
 * 中介者模式示例
 * Created by Administrator on 2018/2/8.
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();

        HouseOwner houseOwner = new HouseOwner("张三", mediator);
        Tenant tenant = new Tenant("李四", mediator);

        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.contact("房主，你那儿有房吗？");
        houseOwner.contact("有...");
    }
}
/*
模式优缺点
优点
1、 简化了对象之间的关系，将系统的各个对象之间的相互关系进行封装，将各个同事类解耦，使系统成为松耦合系统。
2、 减少了子类的生成。
3、 可以减少各同事类的设计与实现。

缺点
由于中介者对象封装了系统中对象之间的相互关系，导致其变得非常复杂，使得系统维护比较困难。

模式适用场景
1、 系统中对象之间存在比较复杂的引用关系，导致他们之间的依赖关系结构混乱而且难以复用该对象。
2、 想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。

模式总结
1、 在中介者模式中通过引用中介者对象，将系统中有关的对象所引用的其他对象数目减少到最少。它简化了系统的结构，将系统由负责的网状结构转变成简单的星形结构，中介者对象在这里起到中转和协调作用。
2、 中介者类是中介者模式的核心，它对整个系统进行控制和协调，简化了对象之间的交互，还可以对对象间的交互进行进一步的控制。
3、 通过使用中介者模式，具体的同事类可以独立变化，通过引用中介者可以简化同事类的设计和实现。
4、 就是由于中介者对象需要知道所有的具体同事类，封装具体同事类之间相互关系，导致中介者对象变得非常复杂，系统维护起来较为困难。

应用实例：
1、中国加入 WTO 之前是各个国家相互贸易，结构复杂，现在是各个国家通过 WTO 来互相贸易。
2、机场调度系统。
3、MVC 框架，其中C（控制器）就是 M（模型）和 V（视图）的中介者。
 */
