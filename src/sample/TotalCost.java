package sample;

public class TotalCost {
    private double km;
    private String cost;

    public TotalCost(double km, String cost) {
        this.km = km;
        this.cost = cost;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
