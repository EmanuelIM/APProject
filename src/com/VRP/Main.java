package com.VRP;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Reader r = new Reader();
        r.readValues();
        VRPAlg vrp = new VRPAlg(r.getDimension(), r.getNumberOfCars(), r.getD(), r.getC());
        vrp.solveVRP();
    }
}
