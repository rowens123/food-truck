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
    public static void update() {
    /*
        //We proxied to local host, but learned that local host does hot transfer to Android Emulator.
        //Temp Code:
        distance.add(43L);
        distance.add(45L);
        distance.add(45L);
        distance.add(47L);
        distance.add(64L);
        probability.add(41L);
        probability.add(40L);
        probability.add(35L);
        probability.add(41L);
        probability.add(69L);
        street.add("Dolores Street From 29th Street To Day Street");
        street.add("Dolores Street From Day Street To 29th Street");
        street.add("Dolores Street From Day Street To 30th Street");
        street.add("Dolores Street From 30th Street To Day Street");
        street.add("Day Street From San Jose Avenue To Dolores Street");

    */

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        JSONParser jsonParser = new JSONParser();
        try {


            URL url = new URL("https://raw.githubusercontent.com/bananamtieu/fTruck/main/parking.json");
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

}
