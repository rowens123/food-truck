package edu.scu.foodtruck;

import java.util.ArrayList;
import java.util.Comparator;

public class ParkingSpace {
    String address;
    int radius;
    int cost;
    int probability;

    public ParkingSpace(String newAddress, int newRadius, int newCost, int newProb) {
        address = newAddress;
        radius = newRadius;
        cost = newCost;
        probability = newProb;
    }

    public String getAddress(){
        return address;
    }

    public int getRadius(){
        return radius;
    }

    public int getCost(){
        return cost;
    }

    public void setAddress(String s) { address = s; }

    public int getProbability(){return probability;}

    public int compareTo(ParkingSpace other) {
        int otherRadius = other.getRadius();

        return radius-otherRadius;
    }

    public static ArrayList getParkingSpacesTest() {
        ArrayList<ParkingSpace> m = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            ParkingSpace n = new ParkingSpace("Location: " + i, i * 5, i * 10, i * 10);
            m.add(n);
        }
        return m;
    }

    public Comparator<ParkingSpace> priceComparison = new Comparator<ParkingSpace>() {
        @Override
        public int compare(ParkingSpace parkingSpace, ParkingSpace t1) {
            int oneCost = parkingSpace.getCost();
            int secondCost = t1.getCost();

            return oneCost - secondCost;
        }
        public int compareProbability(ParkingSpace other){
            int otherProb = other.getProbability();
            return probability-otherProb;
        }
    };
}
