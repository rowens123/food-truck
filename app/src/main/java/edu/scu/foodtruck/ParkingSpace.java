package edu.scu.foodtruck;

import java.util.ArrayList;

public class ParkingSpace {
    String address;
    int radius;
    int probability;
    double latitude;
    double longitude;

    public ParkingSpace(String newAddress, int newRadius, int newProb, double lat, double lon) {
        address = newAddress;
        radius = newRadius;
        probability = newProb;
        latitude = lat;
        longitude = lon;
    }

    public String getAddress(){
        return address;
    }

    public int getRadius(){
        return radius;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }


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
            double latitude = getData.getLatitude().get(i);
            double longitude = getData.getLongitude().get(i);

            m.add(new ParkingSpace(address, (int)distance, (int)probability, (double)latitude, (double)longitude));
        }
        return m;
    }
}
