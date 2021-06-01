package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimAnnealingTSP {
    //Returns the euclidean distance between 2 points in 2D
    double euclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    //Calculates the cost of a trip through a number of destinations
    double calculate_cost(double[][] dist, ArrayList<Integer> permutation, int dimension) {
        double S = 0;
        for (int i = 0; i <= dimension - 2; ++i) {
            S += dist[permutation.get(i)][permutation.get(i + 1)];
        }
        S += dist[permutation.get(dimension - 1)][permutation.get(0)];
        return S;
    }

    //Reverses an array between two randomly chosen points
    void reverseArray(ArrayList<Integer> permutation, int dimension) {
        int firstPosition, secondPosition;
        Random rand = new Random();
        firstPosition = rand.nextInt(dimension) + 1;
        secondPosition = rand.nextInt(dimension) + 1;
        if (firstPosition >= dimension) {
            firstPosition--;
        }
        if (secondPosition >= dimension) {
            secondPosition--;
        }
        if (firstPosition > secondPosition) {
            int auxSwap = firstPosition;
            firstPosition = secondPosition;
            secondPosition = auxSwap;
        }
        Collections.reverse(permutation.subList(firstPosition, secondPosition));
    }

    //Generates 2 random positions, takes the element from the second position and inserts it in the first one
    void insertInArray(ArrayList<Integer> permutation, int dimension) {
        int firstPosition, secondPosition;
        Random rand = new Random();
        firstPosition = rand.nextInt(dimension) + 1;
        secondPosition = rand.nextInt(dimension) + 1;
        if (firstPosition >= dimension) {
            firstPosition--;
        }
        if (secondPosition >= dimension) {
            secondPosition--;
        }
        if (firstPosition > secondPosition) {
            int auxSwap = firstPosition;
            firstPosition = secondPosition;
            secondPosition = auxSwap;
        }
        permutation.add(firstPosition, permutation.get(secondPosition));
        permutation.remove(secondPosition + 1);
    }

    //Swaps two randomly chosen elements in our array
    void swapInArray(ArrayList<Integer> permutation, int dimension) {
        int firstPosition, secondPosition;
        Random rand = new Random();
        firstPosition = rand.nextInt(dimension) + 1;
        secondPosition = rand.nextInt(dimension) + 1;
        if (firstPosition >= dimension) {
            firstPosition--;
        }
        if (secondPosition >= dimension) {
            secondPosition--;
        }
        if (firstPosition > secondPosition) {
            int auxSwap = firstPosition;
            firstPosition = secondPosition;
            secondPosition = auxSwap;
        }
        Collections.swap(permutation, firstPosition, secondPosition);
    }

    //Simulated annealing algorithm for the TSP problem
    //Generates a random candidate solution and uses Wang's greedy operator to find better ones
    ArrayList<Integer> simulated_annealing(double[][] dist, int dimension, ArrayList<Integer> finishedList) {
        double temperature = 100.0;
        double minimumDistance, minimumDistanceNeighbor, minimumDistanceSwapped, minimumDistanceInversed, minimumDistanceInserted;
        ArrayList<Integer> candidate = new ArrayList<Integer>();
        ArrayList<Integer> best_neighbor = new ArrayList<Integer>();
        ArrayList<Integer> neighbor_swap = new ArrayList<Integer>();
        ArrayList<Integer> neighbor_inverse = new ArrayList<Integer>();
        ArrayList<Integer> neighbor_insert = new ArrayList<Integer>();
        for (int i = 1; i < dimension; ++i) {
            if (!finishedList.contains(i)) {
                candidate.add(i);
            }
        }
        candidate.add(0, 0);
        Collections.shuffle(candidate.subList(1, dimension - finishedList.size()));
        minimumDistance = calculate_cost(dist, candidate, dimension - finishedList.size());
        double terminationCondition = Math.pow(10, -8);
        while (temperature > terminationCondition) {
            for (int t = 1; t <= 1000; ++t) {
                minimumDistanceNeighbor = Integer.MAX_VALUE;
                neighbor_inverse.clear();
                neighbor_inverse.addAll(candidate);
                neighbor_insert.clear();
                neighbor_insert.addAll(candidate);
                neighbor_swap.clear();
                neighbor_swap.addAll(candidate);
                reverseArray(neighbor_inverse, dimension - finishedList.size());
                minimumDistanceInversed = calculate_cost(dist, neighbor_inverse, dimension - finishedList.size());
                if (minimumDistanceInversed < minimumDistanceNeighbor) {
                    minimumDistanceNeighbor = minimumDistanceInversed;
                    best_neighbor.clear();
                    best_neighbor.addAll(neighbor_inverse);
                }
                insertInArray(neighbor_insert, dimension - finishedList.size());
                minimumDistanceInserted = calculate_cost(dist, neighbor_insert, dimension - finishedList.size());
                if (minimumDistanceInserted < minimumDistanceNeighbor) {
                    minimumDistanceNeighbor = minimumDistanceInserted;
                    best_neighbor.clear();
                    best_neighbor.addAll(neighbor_insert);
                }
                swapInArray(neighbor_swap, dimension - finishedList.size());
                minimumDistanceSwapped = calculate_cost(dist, neighbor_swap, dimension - finishedList.size());
                if (minimumDistanceSwapped < minimumDistanceNeighbor) {
                    minimumDistanceNeighbor = minimumDistanceSwapped;
                    best_neighbor.clear();
                    best_neighbor.addAll(neighbor_swap);
                }
                if (minimumDistanceNeighbor < minimumDistance) {
                    minimumDistance = minimumDistanceNeighbor;
                    candidate.clear();
                    candidate.addAll(best_neighbor);
                } else {
                    if (Math.random() < Math.exp((-Math.abs(minimumDistanceNeighbor - minimumDistance)) / temperature)) {
                        minimumDistance = minimumDistanceNeighbor;
                        candidate.clear();
                        candidate.addAll(best_neighbor);
                    }
                }
            }
            temperature *= 0.99;
        }

        return candidate;
    }
}
