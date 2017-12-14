package com.hai.ijavase.iservlet.servlet3;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AsyncDemoServletExecutor implements Runnable {
    private AsyncContext ctx = null;

    public AsyncDemoServletExecutor(AsyncContext ctx) {
        this.ctx = ctx;
    }

    public void run() {
        try {
            PrintWriter out = ctx.getResponse().getWriter();
            ctx.start(new Runnable() {
                @Override
                public void run() {
                    out.write("<br>AsyncContext.Runnable start...");
                    //等待十秒钟，以模拟业务方法的执行
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    out.write("<br>AsyncContext.Runnable end...");
                }
            });

            ctx.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    out.println("<br>AsyncContext.AsyncListener.onComplete...");
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    out.println("<br>AsyncContext.AsyncListener.onTimeout...");
                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    out.println("<br>AsyncContext.AsyncListener.onError...");
                }

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    out.println("<br>AsyncContext.AsyncListener.onStartAsync...");
                }
            });
            out.println("<br>业务处理完毕的时间：" + new Date() + ".");
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}