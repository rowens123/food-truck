package edu.scu.foodtruck;

import java.util.ArrayList;

public class ParkingSpace {
    String address;
    int radius;
    int probability;

    public ParkingSpace(String newAddress, int newRadius, int newProb) {
        address = newAddress;
        radius = newRadius;
        probability = newProb;
    }

    public String getAddress(){
        return address;
    }

    public int getRadius(){
        return radius;
    }


    public void setAddress(String s) { address = s; }

    public int getProbability(){return probability;}

    public int compareTo(ParkingSpace other) {
        int otherRadius = other.getRadius();

        return radius-otherRadius;
    }

    public static ArrayList getParkingSpacesTest() {
        ArrayList<ParkingSpace> m = new ArrayList<>();
        GetData getData = new GetData();

        getData.update();
        int dataSize = getData.getStreet().size();
        for (int i =0; i < dataSize; i++) {
            long distance = getData.getDistance().get(i);
            long probability = getData.getProbability().get(i);
            String address = getData.getStreet().get(i);
            m.add(new ParkingSpace(address, (int)distance, (int)probability));
        }
        return m;
    }
}
