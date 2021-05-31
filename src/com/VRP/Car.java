package com.VRP;

import java.util.ArrayList;

public class Car {
    private String carName;
    private double capacity;
    private double originalCapacity;
    private ArrayList<Integer> destinations;
    private double totalCost;
    private ArrayList<Package> packagesDelivered;

    public Car() {
        totalCost = 0;
        destinations = new ArrayList<>();
        capacity = 0;
        originalCapacity = 0;
        packagesDelivered = new ArrayList<>();
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void addDestination(Integer i){
        destinations.add(i);
    }

    public boolean hasDestination(Integer i){
        return destinations.contains(i);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Integer> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Integer> destinations) {
        this.destinations = destinations;
    }

    public ArrayList<Package> getPackagesDelivered() {
        return packagesDelivered;
    }

    public void setPackagesDelivered(ArrayList<Package> packagesDelivered) {
        this.packagesDelivered = packagesDelivered;
    }

    public void addPackage(Package p){
        packagesDelivered.add(p);
    }

    public double getOriginalCapacity() {
        return originalCapacity;
    }

    public void setOriginalCapacity(double originalCapacity) {
        this.originalCapacity = originalCapacity;
    }
}
