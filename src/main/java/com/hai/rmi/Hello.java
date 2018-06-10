package com.hai.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 创建远程方法接口，该接口必须继承自Remote接口
 * Remote 接口是一个标识接口，用于标识所包含的方法可以从非本地虚拟机上调用的接口，Remote接口本身不包含任何方法
 * Created by Administrator on 2018/4/24.
 */
public interface Hello extends Remote {
    //由于远程方法调用的本质依然是网络通信，只不过隐藏了底层实现，网络通信是经常会出现异常的，所以接口的所有方法都必须抛出RemoteException以说明该方法是有风险的
    String sysHello(String msg) throws RemoteException;
}
