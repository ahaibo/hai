package com.hai.ws.adapter;

import com.hai.ws.adapter.model.MyRole;
import com.hai.ws.entity.Role;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */
public class RoleMapAdapter extends XmlAdapter<MyRole[], Map<String, List<Role>>> {

    @Override
    public Map<String, List<Role>> unmarshal(MyRole[] v) throws Exception {
        Map<String, List<Role>> map = new HashMap<>();
        for (MyRole myRole : v) {
            map.put(myRole.getKey(), myRole.getRoles());
        }
        return map;
    }

    @Override
    public MyRole[] marshal(Map<String, List<Role>> v) throws Exception {
        MyRole[] myRoles = new MyRole[v.size()];
        int i = 0;
        for (Map.Entry<String, List<Role>> entry : v.entrySet()) {
            myRoles[i] = new MyRole(entry.getKey(), entry.getValue());
            i++;
        }
        return myRoles;
    }

}
