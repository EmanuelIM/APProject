package sample;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int idCar;
    private List<String> locationName = new ArrayList<>();
    private double totalCost;
    private double totalAmount;

    public Solution(int idCar, List<String> locationName, double totalCost, double totalAmount) {
        this.idCar = idCar;
        this.locationName = locationName;
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
    }

    public Solution(double totalCost, double totalAmount) {
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
    }

    public Solution(int carid, List<String> locationName) {
        this.idCar = carid;
        this.locationName = locationName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Solution() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setLocationName(List<String> locationName) {
        this.locationName = locationName;
    }

    public List<String> getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName.add(locationName);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "idCar='" + idCar + '\'' +
                ", locationName=" + locationName +
                '}';
    }
}
