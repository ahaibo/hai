package com.hai.ws.api;

import com.hai.ws.adapter.RoleMapAdapter;
import com.hai.ws.entity.Role;
import com.hai.ws.entity.User;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */
@WebService
public interface IHelloWorld {
    String say(String msg);

    //处理bean数据类型
    List<Role> getRoleByUser(User user);

    //获取所有用户以及对应的角色，处理Map等复杂类型，此处需要类型转换器辅助
    @XmlJavaTypeAdapter(RoleMapAdapter.class)
    Map<String, List<Role>> getRoles();
}
