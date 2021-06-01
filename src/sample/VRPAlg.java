package sample;

import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VRPAlg {
    private int dimension;
    private int numberOfCars;
    private Destination[] d;
    private Car[] c;

    public VRPAlg(int dimension, int numberOfCars, Destination[] d, Car[] c) {
        this.dimension = dimension;
        this.numberOfCars = numberOfCars;
        this.d = d;
        this.c = c;
    }

    public void solveVRP(ObservableList<Solution> solutions) {
        //We declare a list which keeps all the destinations we already visited.
        ArrayList<Integer> finishedList = new ArrayList<>();
        SimAnnealingTSP sa = new SimAnnealingTSP();
        int cars = 0;
        double costAtEnd = 0;
        //Calculating the cost of travelling between either two destinations.
        double[][] costMatrix = new double[dimension + 1][dimension + 1];
        for (int i = 0; i <= dimension - 1; ++i) {
            for (int j = i; j <= dimension - 1; ++j) {
                if (i != j) {
                    costMatrix[i][j] = sa.euclideanDistance(d[i].getX(), d[i].getY(), d[j].getX(), d[j].getY());
                    costMatrix[j][i] = costMatrix[i][j];
                }
            }
        }
        //We check the destinations which don't have any packages and add them to our finished destinations list.
        for (int i = 1; i <= dimension - 1; ++i) {
            if (d[i].getRequiredPackages().size() == 0) {
                finishedList.add(i);
            }
        }
        while (finishedList.size() < dimension - 1) {
            System.out.println(finishedList);
            ArrayList<Integer> result;
            //We use TSP to calculate the shortest road from the depot through our left destinations
            //We then get the current car and try to fit in as many packages as possible in it
            result = sa.simulated_annealing(costMatrix, dimension, finishedList);
            int range = dimension - finishedList.size() - 1;
            for (int i = 1; i <= range; ++i) {
                ArrayList<Package> packages = d[result.get(i)].getRequiredPackages();
                ArrayList<Package> packagesCopy = new ArrayList<>();
                packagesCopy.addAll(packages);
                boolean ok = true;
                //Fitting the packages in our car
                for (int j = 0; j < packages.size() && ok; ++j) {
                    double currentCarCapacity = c[cars].getCapacity();
                    double currentPackageWeight = packages.get(j).getWeight();
                    System.out.println(currentCarCapacity + " " + currentPackageWeight);
                    //If the package fits, we add the destination and the package to our car
                    if (currentPackageWeight <= currentCarCapacity) {
                        packages.get(j).setDestination(d[result.get(i)]);
                        c[cars].addPackage(packages.get(j));
                        c[cars].setCapacity(currentCarCapacity - currentPackageWeight);
                        packagesCopy.remove(packages.get(j));
                        if (!c[cars].hasDestination(result.get(i) + 1)) {
                            c[cars].addDestination(result.get(i) + 1);
                        }
                        if (j == packages.size() - 1) {
                            //If the packages are finished, we add the destination to our finished list
                            finishedList.add(result.get(i));
                        }
                    } else {
                        //If the package does not fit, we add the destination 0 (depot) to our car and move on to the next car
                        c[cars].addDestination(0);
                        ok = false;
                    }
                }
                packages.clear();
                packages.addAll(packagesCopy);
                double cost = sa.calculate_cost(costMatrix, c[cars].getDestinations(), c[cars].getDestinations().size());
                double costOfCar = c[cars].getTotalCost();
                //We multiply cost by 2 to take in account the car returning to the depot.
                c[cars].setTotalCost(costOfCar + (cost * 2));
                //If car was filled, we reset the capacity, set the destination back to the depot to collect other packages..
                //..and we move on to the next car
                if (!ok) {
                    c[cars].setCapacity(c[cars].getOriginalCapacity());
                    cars++;
                    //Go back to the first car if we are at the last one
                    if (cars == numberOfCars) cars = 0;
                    Package p = new Package();
                    p.setDestination(d[0]);
                    c[cars].addPackage(p);
                }
            }
        }
        for (int i = 0; i < numberOfCars; ++i) {
            Solution solution = new Solution();
            solution.setIdCar(c[i].getId());
            System.out.println("Car " + (i + 1) + ":");
            System.out.println("Car capacity: " + c[i].getOriginalCapacity());
            ArrayList<Package> packages = c[i].getPackagesDelivered();
            System.out.print("Path for this car: ");
            if (i == 0) System.out.print("DEPOT ");
            int packages_delivered = 0;
            for (Package aPackage : packages) {
                if (aPackage.getDestination().getIndex() > 1) {
                    packages_delivered++;
                    System.out.print(aPackage.getDestination().getIndex());
                } else System.out.print("DEPOT");
                System.out.print(" ");
            }
            System.out.println("DEPOT");
            System.out.println("PACKAGES DELIVERED:" + packages_delivered);
            System.out.println("DEPARTED THE DEPOT");
            solution.setLocationName("Depot");
            for (int j = 0; j < packages.size(); ++j) {
                if (packages.get(j).getDestination().getIndex() != 1) {
                    solution.setLocationName(packages.get(j).getDestination().getName());
                    System.out.println("Delivered Package with weight " + packages.get(j).getWeight() + " to destination " + packages.get(j).getDestination().getIndex());
                } else if (j != 0 && (j + 1) < packages.size()) {
                    solution.setLocationName("Depot");
                    System.out.println("RETURNED TO DEPOT TO GET OTHER PACKAGES...");
                }
            }
            solution.setLocationName("Depot");
            System.out.println("RETURNED TO THE DEPOT.");
            costAtEnd += c[i].getTotalCost();
            System.out.println("Total kilometers for this car: " + c[i].getTotalCost());
            System.out.println();
            solution.setTotalCost(c[i].getTotalCost());
            solutions.add(solution);
        }
        DecimalFormat ft;
        double price = ((costAtEnd) / 10) * 3;
        ft = new DecimalFormat("$###,###.##");
        System.out.println("Number of kilometers:" + costAtEnd);
        System.out.println("Gasoline price (at 3$/liter and 10L / 100km medium fuel consumption): " + ft.format(price));
    }
}

