package edu.scu.foodtruck;

import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;

public class GetData {
    public static ArrayList<Long> distance = new ArrayList<Long>();
    public static ArrayList<Long> probability = new ArrayList<Long>();
    public static ArrayList<String> street = new ArrayList<String>();
    public static ArrayList<Double> latitude = new ArrayList<Double>();
    public static ArrayList<Double> longitude = new ArrayList<Double>();
    public static void update() {

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        JSONParser jsonParser = new JSONParser();
        try {
//https://raw.githubusercontent.com/bananamtieu/fTruck/main/parking.json

            URL url = new URL("http://10.0.2.2:8080/getData");
            //URLConnection conn = (URLConnection) url.openConnection();
            //InputStream is = new BufferedInputStream(conn.getInputStream());
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(isr);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObject.get("output");

            //Checking whether the JSON array has some value or not
            if (jsonArray != null) {

                //Iterating JSON array
                for (int i = 0; i < jsonArray.size(); i++) {
                    //Adding each element of JSON array into ArrayList
                    JSONArray spot = (JSONArray) jsonArray.get(i);
                    distance.add((long) spot.get(0));
                    probability.add((long) spot.get(1));
                    street.add(spot.get(2).toString());
                    latitude.add((double)spot.get(3));
                    longitude.add((double)spot.get(4));
                }

            }

        } catch (MalformedURLException e) {
            System.out.println("Exception in NetClientGet:- " + e);
        } catch (IOException e) {
            System.out.println("Exception in NetClientGet:- " + e);
        } catch (ParseException e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

    }

    public static ArrayList<Long> getDistance() {
        return distance;
    }
    public static ArrayList<Long> getProbability() {
        return probability;
    }
    public static ArrayList<String> getStreet() {
        return street;
    }
    public static ArrayList<Double> getLatitude() {
        return latitude;
    }
    public static ArrayList<Double> getLongitude() {
        return longitude;
    }


}
