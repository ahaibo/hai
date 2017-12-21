package com.hai.ws.server;

import com.hai.ws.service.HelloWorldImpl;

import javax.xml.ws.Endpoint;

/**
 * JDK 方式启动部署
 * Created by Administrator on 2017/12/13.
 */
public class ServerByJDK {

    //发布地址。访问方式：${address}?wsdl
    protected static final String address = "http://192.168.0.101:8989/helloworld";

    //jdk 自带的ws部署方式
    public static void main(String[] args) {
        System.out.println("web service start");
        HelloWorldImpl helloWorld = new HelloWorldImpl();
        Endpoint.publish(address, helloWorld);
        System.out.println("web service started");
    }
}
