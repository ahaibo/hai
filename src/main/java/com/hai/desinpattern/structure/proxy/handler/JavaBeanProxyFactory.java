package com.hai.desinpattern.structure.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class JavaBeanProxyFactory {
    private static class JavaBeanProxy implements InvocationHandler {
        Map<String, Object> properties = new HashMap<>();

        public JavaBeanProxy(Map<String, Object> properties) {
            this.properties.putAll(properties);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String meth = method.getName();
            if (meth.startsWith("get")) {
                String prop = meth.substring(3);
                Object o = properties.get(prop);
                if (o != null && !method.getReturnType().isInstance(o))
                    throw new ClassCastException(o.getClass().getName() +
                            " is not a " + method.getReturnType().getName());
                return o;
            } else if (meth.startsWith("set")) {
                // Dispatch setters similarly
            } else if (meth.startsWith("is")) {
                // Alternate version of read for boolean properties
            } else {
                // Can dispatch non read/write/is methods as desired
            }
            return null;//TODO 待完善
        }
    }

    public static <T> T getProxy(Class<T> intf, Map<String, Object> values) {
        return (T) Proxy.newProxyInstance(JavaBeanProxyFactory.class.getClassLoader(), new Class[]{intf}, new JavaBeanProxy(values));
    }
}