package com.hai.desinpattern.other.businessdelegate;

public class Client {

    private BusinessDelegate businessDelegate;

    public Client(BusinessDelegate businessService) {
        this.businessDelegate = businessService;
    }

    public void doTask() {
        businessDelegate.doTask();
    }
}