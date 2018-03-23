/**
 *
 */
package com.hai.javase.proxy;

/**
 * @author Administrator
 */
public class ProxyService {
    private String name;

    public void sayHello(String msg) {
        System.out.println(getName() + ": hello world! " + msg);
    }

    public ProxyService() {
    }

    /**
     * @param name
     */
    public ProxyService(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
