package com.VRP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int dimension;
        double weight;
        ArrayList<Integer> finishedList = new ArrayList<>();
        File myObj = new File("src/com/VRP/input.txt");
        System.out.println(myObj.getAbsoluteFile());
        Scanner myScanner = new Scanner(myObj);
        dimension = myScanner.nextInt();
        weight = myScanner.nextDouble();
        System.out.println(dimension);
        Destination[] d = new Destination[dimension + 1];
        while (myScanner.hasNextLine()) {
            int index = myScanner.nextInt();
            System.out.println(index);
            d[index - 1] = new Destination();
            d[index - 1].setX(myScanner.nextDouble());
            d[index - 1].setY(myScanner.nextDouble());
            if (index == dimension) break;
        }

        while (myScanner.hasNextLine()) {
            int index = myScanner.nextInt();
            System.out.println(index);
            d[index - 1].setWeightOfPackages(myScanner.nextDouble());
        }
        myScanner.close();
        Algorithm sa = new Algorithm();
        double[][] costMatrix = new double[dimension + 1][dimension + 1];
        for (int i = 0; i <= dimension - 1; ++i) {
            for (int j = i; j <= dimension - 1; ++j) {
                if (i != j) {
                    costMatrix[i][j] = sa.euclideanDistance(d[i].getX(), d[i].getY(), d[j].getX(), d[j].getY());
                    costMatrix[j][i] = costMatrix[i][j];
                }
            }
        }
        int cars = 0;
        double totalCost = 0;
        while (finishedList.size() < dimension - 1) {
            cars++;
            System.out.println("Car " + cars + " will have this route:");
            System.out.print("Depot -> ");
            ArrayList<Integer> result;
            result = sa.simulated_annealing(costMatrix, dimension, finishedList);
            double currentWeight = 0;
            int range = dimension - finishedList.size() - 1;
            ArrayList<Integer> destinations = new ArrayList<>();
            destinations.add(0);
            for (int i = 1; i <= range; ++i) {

                if (currentWeight + d[result.get(i)].getWeightOfPackages() <= weight) {
                    currentWeight += d[result.get(i)].getWeightOfPackages();
                    System.out.print(result.get(i) + 1 + " ");
                    destinations.add(result.get(i));
                    finishedList.add(result.get(i));
                } else break;
            }
            double cost = sa.calculate_cost(costMatrix,destinations,destinations.size());
            System.out.println();
            System.out.println("Cost for the first car: " + cost);
            totalCost += cost;
        }
        System.out.println(cars + " cars were needed");
        System.out.println("Total cost: " + totalCost);
    }
}
