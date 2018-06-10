package com.hai.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2018/4/24.
 */
public class HelloClient{
    public static void main(String[] args) {
        try{
            Hello hello= (Hello) Naming.lookup("rmi://192.168.153.1:12312/hello");
            System.out.println(hello.sysHello("hai"));
        }catch (MalformedURLException e) {
            System.out.println("url格式异常");
        } catch (RemoteException e) {
            System.out.println("创建对象异常");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("对象未绑定");
        }
    }
}
