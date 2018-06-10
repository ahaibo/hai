package com.hai.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2018/4/24.
 */
public class HelloServer {
    public static void main(String[] args) {
        try{
            Hello hello=new HelloImpl();
            //注册服务
            LocateRegistry.createRegistry(12312);
            //binding rmi://host:port/name
            //主机和端口都是可选的，如果省略主机，则默认运行在本地；如果端口也省略，则默认端口是1099
            Naming.bind("rmi://192.168.153.1:12312/hello",hello);
            System.out.println("HelloServer启动成功");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
