package com.hai.ijavase.iservlet.graphic;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 图片绘制过程处理器
 * Created by Administrator on 2017/10/6.
 */
public abstract class HaiGraphicAction {

    public Object action(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(getClass().getName() + ".action start...");
        init(request, response);
        beforeAction(request, response);
        processAction(request, response);
        afterAction(request, response);
        if (toDestroy()) {
            destroy(request, response);
        }
        System.out.println(getClass().getName() + ".action end...");
        return null;
    }

    protected boolean toDestroy() {
        return true;
    }

    protected abstract Object init(ServletRequest request, ServletResponse response) throws Exception;

    protected abstract Object beforeAction(ServletRequest request, ServletResponse response) throws Exception;

    protected abstract Object processAction(ServletRequest request, ServletResponse response) throws Exception;

    protected abstract Object afterAction(ServletRequest request, ServletResponse response) throws Exception;

    protected abstract Object destroy(ServletRequest request, ServletResponse response) throws Exception;

}
