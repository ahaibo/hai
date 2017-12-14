package com.hai.test.suggest151;

/**
 * 不要重写静态方法，但可隐藏
 * <p>
 * 对象类型：表面类型、实际类型
 * <p>
 * 实例对象调用静态方法是根据表面类型去查找的
 * 类型方式调用静态方法是根据实际类型去查找的
 * Created by Administrator on 2017/9/28.
 */
public class Sug033_OverrideStatic {
    public static void main(String[] args) {
        Base base = new Sub();
        base.doAnything();
        base.doSomething();

        System.out.println("------------------------------");

        Base.doSomething();
        Sub.doSomething();
    }
}

class Base {
    public static void doSomething() {
        System.out.println(Base.class.getName() + ".doSomething...");
    }

    public void doAnything() {
        System.out.println(this.getClass().getName() + ".doAnything...");
    }
}

class Sub extends Base {
    //此种方式叫隐藏(Hide)
    public static void doSomething() {
        System.out.println(Sub.class.getName() + ".doSomething...");
    }

    //Override
    public void doAnything() {
        System.out.println(this.getClass().getName() + ".doAnything...");
    }
}
