package com.hai.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/13.
 */
@Controller("/")
public class Application {

    @RequestMapping("")
    public String index() {
        System.out.println(this.getClass().getName() + ".index...");
        return "index";
    }
}
