package sample;


public class Package {
    private String contents;
    private double weight;
    private Destination destination;

    public Package() {

    }

    public Package(String contents, double weight, Destination destination) {
        this.contents = contents;
        this.weight = weight;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
