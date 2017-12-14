package com.hai.ijavase.desinpattern.statemode;

public interface State {
    public void insertCoin();

    public void returnCoin();

    public void turnCrank();

    public void dispense();

    public void printstate();
}