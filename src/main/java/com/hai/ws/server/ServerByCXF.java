package com.hai.ws.server;

import com.hai.ws.api.IHelloWorld;
import com.hai.ws.intercepter.AuthIntercepter;
import com.hai.ws.service.HelloWorldImpl;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by Administrator on 2017/12/13.
 */
public class ServerByCXF {

    //发布地址。访问方式：${address}?wsdl
    protected static final String address = "http://192.168.0.101:8090/helloworld";

    //cxf 方式部署 ws
    public static void main(String[] args) {
        System.out.println("web service start");
        HelloWorldImpl helloWorld = new HelloWorldImpl();
//        Endpoint.publish(address, helloWorld);

        //这儿用到了 cxf-rt-transports-http-jetty 做服务器
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress(address);

        //设置服务接口、实现类
        factoryBean.setServiceClass(IHelloWorld.class);
        factoryBean.setServiceBean(helloWorld);

        //添加拦截器：系统拦截器
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

        //自定义拦截器
        factoryBean.getInInterceptors().add(new AuthIntercepter());

//        factoryBean.getInFaultInterceptors().add(new LoggingInInterceptor());
//        factoryBean.getOutFaultInterceptors().add(new LoggingOutInterceptor());

        //创建服务
        factoryBean.create();

        System.out.println("web service started");
    }

}
