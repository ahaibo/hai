package com.hai.javase.desinpattern.businessdelegate;

public class BusinessLookUp {
    public BusinessService getBusinessService(String serviceType) {
        BusinessService service = null;
        switch (serviceType) {
            case "EJB":
                service = new EJBService();
                break;
            case "JMS":
                service = new JMSService();
                break;
            default:
                service = new EJBService();
                break;
        }
        return service;
    }
}