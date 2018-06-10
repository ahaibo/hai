package com.hai.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2018/4/24.
 */
public class Registry {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(12312);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
