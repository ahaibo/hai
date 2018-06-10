package com.hai.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2018/4/24.
 */
public class HelloImpl extends UnicastRemoteObject implements Hello {

    //UnicastRemoteObject类的构造函数抛出了RemoteException，故其继承类不能使用默认构造函数，继承类的构造函数必须也抛出RemoteException
    //由于方法参数与返回值最终都将在网络上传输，故必须是可序列化的
    public HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sysHello(String msg) throws RemoteException {
        return "Hello, "+msg;
    }
}
