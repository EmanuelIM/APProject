package sample;

import java.util.ArrayList;

public class Car {
    private double capacity;
    private double originalCapacity;
    private ArrayList<Integer> destinations;
    private double totalCost;
    private ArrayList<Package> packagesDelivered;
    private int id;

    public Car() {
        totalCost = 0;
        destinations = new ArrayList<>();
        capacity = 0;
        originalCapacity = 0;
        packagesDelivered = new ArrayList<>();
    }

    public Car(double originalCapacity, int id) {
        this.originalCapacity = originalCapacity;
        this.id = id;
    }




    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }


    public void addDestination(Integer i) {
        destinations.add(i);
    }

    public boolean hasDestination(Integer i) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPackage(Package p) {
        packagesDelivered.add(p);
    }

    public double getOriginalCapacity() {
        return originalCapacity;
    }

    public void setOriginalCapacity(double originalCapacity) {
        this.originalCapacity = originalCapacity;
    }
}
