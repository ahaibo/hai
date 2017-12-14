package com.hai.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/10/9.
 */
@Controller
@RequestMapping("/pdf")
public class MyPDFController {

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(getClass().getName() + ".test...");
        if (null != request) {
            logParameters(request.getParameterMap());
        }

        if (null != response) {
            System.out.println("response is not null...");
        }
    }

    @RequestMapping("/test2")
    @ResponseBody
    public void test2(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (null != request) {
            logParameters(request.getParameterMap());
        }

        if (null != model) {
            List<Map<String, String>> contents = new ArrayList<>();
            Map<String, String> content = null;
            for (int i = 0; i < 20; i++) {
                content = new HashMap<>();
                content.put("id" + i, i + "");
                content.put("name" + i, i + "");
                content.put("price" + i, i + "");
                contents.add(content);
            }
            model.addAttribute("header", "My PDF Export JacksonTest");
            model.addAttribute("copyright", "copyright My PDF Export JacksonTest@hai");
            model.addAttribute("content", contents);
        }

        List<String> list = Arrays.asList("ID", "NAME", "PRICE");

        if (null != response) {
            System.out.println("response is not null...");
        }

        try {
            response.getWriter().write("success...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logParameters(Map<String, String[]> parameterMap) {
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.print(entry.getKey() + " : ");
            for (String str : entry.getValue()) {
                System.out.print(str + "\r");
            }
            System.out.println();
        }
    }
}
