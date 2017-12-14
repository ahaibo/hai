/**
 * 动态代理
 */
package com.hai.ijavase.desinpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理接口
interface Subject {

    void action();

}

// 被代理类
class RealSubject implements Subject {

    @Override
    public void action() {
        System.out.println("action in proxy class: " + this.getClass().getName());
    }

}

/**
 * 基于代理的 Decorator，为每个方法调用生成调试日志
 *
 * @param <T>
 */
class LoggingInvocationHandler<T> implements InvocationHandler {
    final T underlying;

    public LoggingInvocationHandler(T underlying) {
        this.underlying = underlying;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuffer sb = new StringBuffer();
        sb.append(method.getName());
        sb.append("(");
        for (int i = 0; args != null && i < args.length; i++) {
            if (i != 0)
                sb.append(", ");
            sb.append(args[i]);
        }
        sb.append(")");
        Object ret = method.invoke(underlying, args);
        if (ret != null) {
            sb.append(" -> ");
            sb.append(ret);
        }
        System.out.println(sb);
        return ret;
    }
}

// 动态代理处理器
class MyInvocationHandler implements InvocationHandler {
    Object obj;// 实现了代理接口的被代理类对象

    public Object blind(Object obj) {
        this.obj = obj;
        // 实例化代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    // 代理对象方法调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(this.getClass().getName() + ".invoke() main...");
        MyAOPImpl.getInstance().before();
        Object result = method.invoke(this.obj, args);
        MyAOPImpl.getInstance().after();
        System.out.println(this.getClass().getName() + ".invoke() end...");
        return result;
    }
}

// 模拟AOP部分
interface IMyAOP {
    void before();

    void after();
}

class MyAOPImpl implements IMyAOP {

    private volatile static MyAOPImpl instance;

    private MyAOPImpl() {
    }

    public static MyAOPImpl getInstance() {
        if (null == instance) {
            synchronized (MyAOPImpl.class) {
                if (null == instance) {
                    instance = new MyAOPImpl();
                }
            }
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hai.hai.ijavase.desinpattern.proxy.IMyAOP#before()
     */
    @Override
    public void before() {
        System.out.println(this.getClass().getName() + ".before()...");
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hai.hai.ijavase.desinpattern.proxy.IMyAOP#after()
     */
    @Override
    public void after() {
        System.out.println(this.getClass().getName() + ".after()...");
    }

}

/**
 * @author as
 */
public class DynamicProxy {

    public static void main(String[] args) {
        RealSubject subject = new RealSubject();

        Subject proxy = null;

        proxy = (Subject) new MyInvocationHandler().blind(subject);

//        proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new MyInvocationHandler());

        proxy.action();
    }
}
