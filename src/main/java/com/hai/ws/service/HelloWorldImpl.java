package com.hai.ws.service;

import com.hai.ws.api.IHelloWorld;
import com.hai.ws.entity.Role;
import com.hai.ws.entity.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */
@Component("helloWorld")
@WebService
public class HelloWorldImpl implements IHelloWorld {
    @Override
    public String say(String msg) {
        return "Hello, " + msg;
    }

    @Override
    public List<Role> getRoleByUser(User user) {
        List<Role> roleList = new ArrayList<>();
        // 模拟 直接写死
        if (user != null) {
            if (user.getUserName().equals("java1234") && user.getPassword().equals("123456")) {
                roleList.add(new Role(1, "技术总监"));
                roleList.add(new Role(2, "架构师"));
            } else if (user.getUserName().equals("jack") && user.getPassword().equals("123456")) {
                roleList.add(new Role(3, "程序员"));
            }
        }
        return roleList;
    }

    @Override
    public Map<String, List<Role>> getRoles() {
        Map<String, List<Role>> map = new HashMap<String, List<Role>>();
        List<Role> roleList1 = new ArrayList<Role>();
        roleList1.add(new Role(1, "技术总监"));
        roleList1.add(new Role(2, "架构师"));
        map.put("java1234", roleList1);
        List<Role> roleList2 = new ArrayList<Role>();
        roleList2.add(new Role(1, "程序员"));
        map.put("jack", roleList2);
        return map;
    }
}
