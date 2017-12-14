/**
 *
 */
package com.hai.common.db.mydatasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * 使用JDK动态代理自定义链接【Connection】已满足自定义数据源需求【修改Connection.close的原处理方式，改为将改连接放入到连接池】
 *
 * @author Administrator
 */
public class MyConnection implements InvocationHandler {
    private Connection connection;
    // 包装后的Connection，即【动态】代理对象
    private Connection warpedConnection;
    private MyDataSource myDataSource;
    // 该连接最后在使用多少次后被关闭
    private static final int MAX_USE_COUNT = 10;
    // 该连接当前使用了多少次
    private int currUseCount = 0;

    public MyConnection(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    Connection bind(Connection connection) {
        this.connection = connection;
        this.warpedConnection = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Connection.class}, this);

        return this.warpedConnection;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     * java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 拦截特殊处理被代理对象的close方法【Connection的close方法】
        if (method.getName().equals("close")) {
            this.currUseCount++;
            if (this.currUseCount < MAX_USE_COUNT) {
                // 注意此时添加的连接对象为代理对象
                this.myDataSource.addConnection(this.warpedConnection);
            } else {
                this.connection.close();
                this.myDataSource.setCurrConnCount(this.myDataSource.getCurrConnCount() - 1);
            }
        }
        return method.invoke(this.connection, args);
    }

}
