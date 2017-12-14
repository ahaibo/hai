package com.hai.java8.ibm.model;

public class Car {
    private String make;
    private String model;
    private int year;

    public Car(String theMake, String theModel, int yearOfMake) {
        make = theMake;
        model = theModel;
        year = yearOfMake;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}