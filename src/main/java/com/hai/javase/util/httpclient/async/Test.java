package com.hai.javase.util.httpclient.async;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("HOSTNAME:" + System.getenv("HOSTNAME"));
        System.out.println("COMPUTERNAME:" + System.getenv("COMPUTERNAME"));
        System.out.println("HostAddress:" + Inet4Address.getLocalHost().getHostAddress());
        System.out.println("HostName:" + Inet4Address.getLocalHost().getHostName());
    }
}
