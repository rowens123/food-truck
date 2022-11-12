package edu.scu.foodtruck;

import java.util.Comparator;

public class ParkingSpace {
    String address;
    int radius;
    int cost;

    public ParkingSpace(String newAddress, int newRadius, int newCost) {
        address = newAddress;
        radius = newRadius;
        cost = newCost;
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

    public int compareTo(ParkingSpace other) {
        int otherRadius = other.getRadius();

        return radius-otherRadius;
    }

    public Comparator<ParkingSpace> priceComparison = new Comparator<ParkingSpace>() {
        @Override
        public int compare(ParkingSpace parkingSpace, ParkingSpace t1) {
            int oneCost = parkingSpace.getCost();
            int secondCost = t1.getCost();

            return oneCost - secondCost;
        }
    };
}
