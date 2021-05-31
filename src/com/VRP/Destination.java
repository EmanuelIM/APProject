package com.VRP;

import java.util.ArrayList;

public class Destination {
    private double x,y;
    private ArrayList<Package> requiredPackages;
    private String name;

    public Destination() {
        requiredPackages = new ArrayList<>();
    }

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Package> getRequiredPackages() {
        return requiredPackages;
    }

    public void setRequiredPackages(ArrayList<Package> requiredPackages) {
        this.requiredPackages = requiredPackages;
    }

    public void addPackage(Package p){
        requiredPackages.add(p);
    }
}
