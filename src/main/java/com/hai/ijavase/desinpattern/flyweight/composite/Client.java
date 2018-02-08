package com.hai.ijavase.desinpattern.flyweight.composite;

import com.hai.ijavase.desinpattern.flyweight.Flyweight;
import com.hai.ijavase.desinpattern.flyweight.FlyweightFactory;

import java.util.ArrayList;
import java.util.List;

/*
总结
1、模式适用环境
　　
   在以下情况下可以使用享元模式：
   一个系统有大量相同或者相似的对象，由于这类对象的大量使用，造成内存的大量耗费；
   对象的大部分状态都可以外部化，可以将这些外部状态传入对象中(细粒度对象)；
   使用享元模式需要维护一个存储享元对象的享元池，而这需要耗费资源，因此，应当在多次重复使用享元对象时才值得使用享元模式。

2、模式的优点
　　（1）它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份；
　　（2）享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。

3、模式的缺点
　　（1）享元模式使得系统更加复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化；
　　（2）为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。

4、模式的实现
   享元模式运用共享技术有效地支持大量 细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用，它是一种对象结构型模式。
   享元模式包含四个角色：
     抽象享元类声明一个接口，通过它可以接受并作用于外部状态；
     具体享元类实现了抽象享元接口，其实例称为享元对象；
     非共享具体享元是不能被共享的抽象享元类的子类；
     享元工厂类用于创建并管理享元对象，它针对抽象享元类编程，将各种类型的具体享元对象存储在一个享元池中。

   享元模式以共享的方式高效地支持大量的细粒度对象，享元对象能做到共享的关键是区分内部状态和外部状态。
   其中内部状态是存储在享元对象内部并且不会随环境改变而改变的状态，因此内部状态可以共享；外部状态是随环境改变而改变的、不可以共享的状态。
*/
public class Client {

    public static void main(String[] args) {
        List<Character> compositeState = new ArrayList<Character>();
        compositeState.add('a');
        compositeState.add('b');
        compositeState.add('c');
        compositeState.add('a');
        compositeState.add('b');

        FlyweightFactory flyFactory = new FlyweightFactory();
        Flyweight compositeFly1 = flyFactory.factory(compositeState);
        Flyweight compositeFly2 = flyFactory.factory(compositeState);
        compositeFly1.operation("Composite Call");

        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

        Character state = 'a';
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }
}