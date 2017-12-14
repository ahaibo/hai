package com.hai.ws.server;

import com.hai.ws.service.HelloWorldImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Administrator on 2017/12/13.
 */
public class Server {

    //发布地址。访问方式：${address}?wsdl
    protected static final String address = "http://192.168.0.101:8990/helloworld";

    //jdk 自带的ws部署方式
    public static void main(String[] args) {
        System.out.println("web service start");
        HelloWorldImpl helloWorld = new HelloWorldImpl();
        Endpoint.publish(address, helloWorld);
        System.out.println("web service started");
    }
}
