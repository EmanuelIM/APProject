package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private int numberOfCars;
    private int dimension;
    private Destination[] d;
    private Car[] c;

    public void readValues() throws FileNotFoundException {
        File myObj = new File("src/com/VRP/input.txt");
        Scanner myScanner = new Scanner(myObj);
        dimension = myScanner.nextInt();
        d = new Destination[dimension + 1];
        //Reading all our required info..
        //Reading our destination coordinates (1 is our depot)
        while (myScanner.hasNextLine()) {
            int index = myScanner.nextInt();
            d[index - 1] = new Destination();
            d[index - 1].setX(myScanner.nextDouble());
            d[index - 1].setY(myScanner.nextDouble());
            d[index - 1].setIndex(index);
            if (index == dimension) break;
        }
        //Reading required packages for each destination
        while (myScanner.hasNextLine()) {
            int index = myScanner.nextInt();
            int numberOfPackages = myScanner.nextInt();
            for (int i = 0; i < numberOfPackages; ++i) {
                Package p = new Package();
                p.setWeight(myScanner.nextDouble());
                d[index - 1].addPackage(p);
            }
            if (index == dimension) break;
        }
        //Reading cars and dimensions
        numberOfCars = myScanner.nextInt();
        c = new Car[numberOfCars];
        while (myScanner.hasNextLine()) {
            int index = myScanner.nextInt();
            double capacity = myScanner.nextDouble();
            c[index - 1] = new Car();
            c[index - 1].setCapacity(capacity);
            c[index - 1].setOriginalCapacity(capacity);
            if (index == numberOfCars) break;
        }
        myScanner.close();
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public int getDimension() {
        return dimension;
    }

    public Destination[] getD() {
        return d;
    }

    public Car[] getC() {
        return c;
    }
}
