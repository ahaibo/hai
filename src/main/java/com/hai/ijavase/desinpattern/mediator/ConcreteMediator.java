package com.hai.ijavase.desinpattern.mediator;

/**
 * Created by Administrator on 2018/2/8.
 */
public class ConcreteMediator extends Mediator {
    private HouseOwner houseOwner;
    private Tenant tenant;

    public ConcreteMediator() {
    }

    public ConcreteMediator(HouseOwner houseOwner, Tenant tenant) {
        this.houseOwner = houseOwner;
        this.tenant = tenant;
    }

    @Override
    public void contact(String message, Person person) {
        if (person instanceof HouseOwner) {//如果是房主，则租房者获得信息
            tenant.message(message);
        } else {
            houseOwner.message(message);
        }
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
