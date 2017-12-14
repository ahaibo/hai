package com.hai.ws.intercepter;

import com.alibaba.fastjson.JSONObject;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;

/**
 * cxf 拦截器：权限拦截
 * Created by Administrator on 2017/12/14.
 */
public class AuthIntercepter extends AbstractPhaseInterceptor<SoapMessage> {

    public AuthIntercepter() {
        this(Phase.PRE_INVOKE);//在调用方法之前调用拦截器
    }

    public AuthIntercepter(String phase) {
        super(phase);//在调用方法之前调用拦截器
        System.out.println(this.getClass().getName() + ".phase: " + phase);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        System.out.println(this.getClass().getName() + ".auth start...");
        Header header = message.getHeader(new QName("auth-header"));
        if (null == header) {
            throw new Fault(new IllegalArgumentException("no auth header"));
        }

        System.out.println(this.getClass().getName() + ".auth-header info\n" + JSONObject.toJSONString(header, true));
        Element element = (Element) header.getObject();
        NodeList usernameNodeList = element.getElementsByTagName("username");
        NodeList passwordNodeList = element.getElementsByTagName("password");
        if (null == usernameNodeList || usernameNodeList.getLength() != 1) {
            new Fault(new IllegalArgumentException("error format for username"));
        }
        if (null == passwordNodeList || passwordNodeList.getLength() != 1) {
            new Fault(new IllegalArgumentException("error format for password"));
        }

        String username = usernameNodeList.item(0).getTextContent();
        String password = passwordNodeList.item(0).getTextContent();
        if (null == username || username.trim().length() == 0 || !username.equals("hai")) {
            new Fault(new IllegalArgumentException("error username"));
        }
        if (null == password || password.trim().length() == 0 || !password.equals("123456")) {
            new Fault(new IllegalArgumentException("error password"));
        }
        System.out.println(this.getClass().getName() + ".auth success...");
    }
}
