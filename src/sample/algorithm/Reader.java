package sample.algorithm;


import sample.objects.Car;
import sample.objects.Destination;
import sample.objects.Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private int numberOfCars;
    private int dimension;
    private Destination[] d;
    private Car[] c;

    public void readValues() throws FileNotFoundException {
        File myObj = new File("src/sample/txtFiles/Destinations.txt");
        Scanner myScanner = new Scanner(myObj);
        numberOfCars = 0;
        dimension = 0;
        int currentIndex = 0;
        d = new Destination[100];
        //Reading all our required info..
        //Reading our destination coordinates (1 is our depot)
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            String[] words = line.split(" ");
            String name = words[0];
            double x = Double.parseDouble(words[1]);
            double y = Double.parseDouble(words[2]);
            double w = Double.parseDouble(words[3]);
            boolean ok = false;
            int foundIndex = 0;
            for (int i = 0; i < currentIndex && !ok; ++i) {
                if (d[i].getX() == x && d[i].getY() == y) {
                    ok = true;
                    foundIndex = i;
                }
            }
            if (!ok) {
                currentIndex++;
                d[currentIndex - 1] = new Destination();
                d[currentIndex - 1].setName(name);
                d[currentIndex - 1].setX(x);
                d[currentIndex - 1].setY(y);
                d[currentIndex - 1].setIndex(currentIndex);
                Package p = new Package();
                p.setWeight(w);
                d[currentIndex - 1].addPackage(p);
                dimension++;
            } else {
                Package p = new Package();
                p.setWeight(w);
                d[foundIndex].addPackage(p);
            }
        }
        myScanner.close();
        File obj = new File("src/sample/txtFiles/Cars.txt");
        Scanner scanner = new Scanner(obj);
        c = new Car[100];
        while (scanner.hasNextLine()) {
            numberOfCars++;
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            c[numberOfCars - 1] = new Car();
            c[numberOfCars - 1].setId(Integer.parseInt(words[0]));
            c[numberOfCars - 1].setCapacity(Double.parseDouble(words[1]));
            c[numberOfCars - 1].setOriginalCapacity(Double.parseDouble(words[1]));
        }
        scanner.close();
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
