package com.VRP;

import java.util.ArrayList;

public class Destination {
    private double x,y;
    private double weightOfPackages;
    private String name;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWeightOfPackages() {
        return weightOfPackages;
    }

    public void setWeightOfPackages(double weightOfPackages) {
        this.weightOfPackages = weightOfPackages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
