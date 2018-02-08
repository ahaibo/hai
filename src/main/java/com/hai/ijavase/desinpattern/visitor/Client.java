package com.hai.ijavase.desinpattern.visitor;

/*
访问者模式主要包含如下几个角色：
Vistor: 抽象访问者。为该对象结构中的ConcreteElement的每一个类声明的一个操作。
ConcreteVisitor: 具体访问者。实现Visitor申明的每一个操作，每一个操作实现算法的一部分。
Element: 抽象元素。定义一个Accept操作，它以一个访问者为参数。
ConcreteElement: 具体元素 。实现Accept操作。
ObjectStructure: 对象结构。能够枚举它的元素，可以提供一个高层的接口来允许访问者访问它的元素。

模式优缺点
优点
1、使得新增新的访问操作变得更加简单。
2、能够使得用户在不修改现有类的层次结构下，定义该类层次结构的操作。
3、将有关元素对象的访问行为集中到一个访问者对象中，而不是分散搞一个个的元素类中。

缺点
1、增加新的元素类很困难。在访问者模式中，每增加一个新的元素类都意味着要在抽象访问者角色中增加一个新的抽象操作，并在每一个具体访问者类中增加相应的具体操作，违背了“开闭原则”的要求。
2、破坏封装。当采用访问者模式的时候，就会打破组合类的封装。
3、比较难理解。貌似是最难的设计模式了。

模式适用场景
1、对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
2、需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。

模式总结
1、访问者模式封装了对象结构元素之上的操作，使得新增元素的操作变得非常简单。所以它比较适用于那么对象结构很少变化的类。
2、访问者模式中对象结构存储了不同类型的元素对象，以供不同访问者访问。
 */
public class Client {
    public static void main(String[] args) {
        Medicine a = new MedicineA("板蓝根", 11.0);
        Medicine b = new MedicineB("感康", 14.3);

        Prescription presciption = new Prescription();
        presciption.addMedicine(a);
        presciption.addMedicine(b);

        Visitor charger = new Charger();
        charger.setName("张三");

        Visitor workerOfPharmacy = new WorkerOfPharmacy();
        workerOfPharmacy.setName("李四");

        presciption.accept(charger);
        System.out.println("-------------------------------------");
        presciption.accept(workerOfPharmacy);
    }
}