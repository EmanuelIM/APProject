package sample;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private String idCar;
    private List<String> locationName = new ArrayList<>();


    public Solution(String carid, List<String> locationName) {
        this.idCar = carid;
        this.locationName = locationName;
    }

    public Solution() {
    }

    public String getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    public void setLocationName(List<String> locationName) {
        this.locationName = locationName;
    }

    public void setCarid(String carid) {
        this.idCar = carid;
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
