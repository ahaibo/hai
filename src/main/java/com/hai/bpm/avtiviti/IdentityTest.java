package com.hai.bpm.avtiviti;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/12/10.
 */
public class IdentityTest extends BaseActiviti {

    @Test
    public void saveUser() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("zhangsan");
//        user.setFirstName("zhang");
//        user.setLastName("san");
        user.setEmail("12345678@qq.com");
        user.setPassword("123456");
//        user.setInserted(true);
//        user.setUpdated(false);
//        ProcessEngines.getDefaultProcessEngine().getIdentityService().saveUser(user);
        identityService.saveUser(user);
        printUser(user);
//        printUser(identityService.newUser("lisi"));
    }

    @Test
    public void deleteUser() {
        identityService.deleteUser("zhangsan");
    }

    @Test
    public void saveGroup() {
        Group group = new GroupEntityImpl();
        group.setId("zhangsan");
        group.setName("group1");
        identityService.saveGroup(group);
        Group newGroup = identityService.newGroup("group2");
        System.out.println(JSONObject.toJSONString(group) + "\n");
        System.out.println(JSONObject.toJSONString(newGroup) + "\n");
    }

    @Test
    public void deleteGroup() {
        identityService.deleteGroup("group1");
    }

    @Test
    public void createMembership() {
        identityService.createMembership("zhangsan", "group1");
    }

    @Test
    public void deleteMembership() {
        identityService.deleteMembership("zhangsan", "group1");
    }

    @Test
    public void memberOfGroup() {
        List<User> users = identityService.createUserQuery().memberOfGroup("group1").list();
        printUsers(users);
    }

    @Test
    public void queryUserById() {
        printUsers(identityService.createUserQuery().userId("zhangsan").list());
    }

    @Test
    public void queryGroupById() {
        printGroups(identityService.createGroupQuery().groupId("zhangsan").list());
    }

    private void printUser(User user) {
        System.out.println(JSONObject.toJSONString(user) + "\n");
    }

    private void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println(JSONObject.toJSONString(user) + "\n");
        }
    }

    private void printGroup(Group group) {
        System.out.println(JSONObject.toJSONString(group) + "\n");
    }

    private void printGroups(List<Group> groups) {
        for (Group group : groups) {
            System.out.println(JSONObject.toJSONString(group) + "\n");
        }
    }
}
