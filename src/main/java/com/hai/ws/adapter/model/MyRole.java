package com.hai.ws.adapter.model;

import com.hai.ws.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */
public class MyRole {
    private String key;
    private List<Role> roles;

    public MyRole() {
    }

    public MyRole(String key, List<Role> roles) {
        this.key = key;
        this.roles = roles;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
