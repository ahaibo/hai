package com.hai.bpm.avtiviti.cache;

import org.activiti.engine.impl.persistence.deploy.DeploymentCache;

/**
 * Created by Administrator on 2017/12/7.
 */
public class MyCache implements DeploymentCache {
    @Override
    public Object get(String s) {
        System.out.println(this.getClass().getName().concat(".get..."));
        return null;
    }

    @Override
    public boolean contains(String s) {
        System.out.println(this.getClass().getName().concat(".contains..."));
        return false;
    }

    @Override
    public void add(String s, Object o) {
        System.out.println(this.getClass().getName().concat(".add..."));
    }

    @Override
    public void remove(String s) {
        System.out.println(this.getClass().getName().concat(".remove..."));
    }

    @Override
    public void clear() {
        System.out.println(this.getClass().getName().concat(".clear..."));
    }
}
