package sample;

import java.util.ArrayList;

public class Destination {
    private double x, y;
    private ArrayList<Package> requiredPackages;
    private String name;
    private int index;
    private double weightOfPackages;

    public Destination() {
        requiredPackages = new ArrayList<>();
    }

    public Destination(double x, double y, ArrayList<Package> requiredPackages, String name, int index) {
        this.x = x;
        this.y = y;
        this.requiredPackages = requiredPackages;
        this.name = name;
        this.index = index;
    }

    public Destination(double x, double y, double weight,String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.weightOfPackages = weight;
    }

    public double getWeightOfPackages() {
        return weightOfPackages;
    }

    public void setWeightOfPackages(double weightOfPackages) {
        this.weightOfPackages = weightOfPackages;
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

    public void addPackage(Package p) {
        requiredPackages.add(p);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
