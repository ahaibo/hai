package com.hai.spring.view.viewresolver;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

public class HtmlResourceView extends InternalResourceView {
    public HtmlResourceView() {
        super();
        System.out.println(this.getClass().getName() + " Created.");
    }

    public HtmlResourceView(String url) {
        super(url);
        System.out.println(this.getClass().getName() + " Created with url: " + url);
    }

    public HtmlResourceView(String url, boolean alwaysInclude) {
        super(url, alwaysInclude);
        System.out.println(this.getClass().getName() + " Created with url: " + url + "; alwaysInclude: " + alwaysInclude);
    }

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        System.out.println(this.getClass().getName() + ".checkResource..");
        File file = new File(this.getServletContext().getRealPath("/"), this.getUrl());
        return file.exists();
    }
}
