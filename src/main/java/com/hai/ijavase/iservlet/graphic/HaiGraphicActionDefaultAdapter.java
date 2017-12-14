package com.hai.ijavase.iservlet.graphic;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Administrator on 2017/10/6.
 */
public class HaiGraphicActionDefaultAdapter extends HaiGraphicAction {
    @Override
    protected Object init(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".init...");
        return null;
    }

    @Override
    protected Object beforeAction(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".beforeAction...");
        return null;
    }

    @Override
    protected Object processAction(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".processAction...");
        return null;
    }

    @Override
    protected Object afterAction(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".afterAction...");
        return null;
    }

    @Override
    protected Object destroy(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".destroy...");
        return null;
    }
}
