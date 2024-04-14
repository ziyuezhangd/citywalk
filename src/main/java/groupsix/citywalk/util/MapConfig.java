package groupsix.citywalk.util;

import groupsix.citywalk.model.PublicTransportMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;

public class MapConfig {
    //Define the size of city map
    public static final int[] mapSize = {10,10};

    //Define the stations
    //Station names
    public static final String[] stationNames = {"Dundrum","Rathmines","Blackrock","UCD","Belfield",
            "Merrion","Donnybrook","TempleBar","Trinity","Ballsbridge",
            "TheSpire","Sandymount","Docklands","DublinZoo","IKEA"};
    //Station locations
    public static final int[][] stationLocations = {{6,9},{2,7},{9,8},{5,8},{3,9},
            {7,7},{5,6},{1,5},{3,4},{6,4},
            {5,3},{9,5},{8,3},{2,2},{6,1}};

    //Define the transport modes
    public static final String[] basicTransportNames = {"Walk", "Bike", "Taxi"};
    public static final String[] publicTransportNames = {"Bus", "Luas", "Dart"};
    public static final String[] allTransportNames = {"Walk", "Bike", "Taxi", "Bus", "Luas", "Dart"};
    public static final double[] allTimeFactors = {7.0,2.4,2.0,3.8,3.0,3.4};  //unit: minute
    public static final int[] allCarbonFactors = {2,4,48,30,15,18};  //unit: gram

    //Design basic transport regions
    public static final int[][][] basicTransportRegion = {{{0,0},{10,10}},{{2,3},{6,6}},{{0,0},{10,10}}};

    //Design public transport routes
    //Define the stops for each public transport mode
    public static final HashMap<String,ArrayList<String>> publicTransportStops = new HashMap<>();
    static {
        publicTransportStops.put("Bus", new ArrayList<>(Arrays.asList("IKEA","TheSpire",
                "Trinity","TempleBar","Rathmines","Belfield")));
        publicTransportStops.put("Luas", new ArrayList<>(Arrays.asList("DublinZoo","TheSpire",
                "Ballsbridge","Donnybrook","UCD","Dundrum")));
        publicTransportStops.put("Dart", new ArrayList<>(Arrays.asList("Belfield","Dundrum","Merrion","Blackrock",
                "Sandymount","Docklands")));
    }
    //Compute the transport options for each station
    public static final HashMap<String, ArrayList<String>> publicTransportOptions = new HashMap<>();
    static {
        for (String station: stationNames){
            ArrayList<String> options = new ArrayList<>();
            for (String transport: publicTransportNames){
                if (publicTransportStops.get(transport).contains(station)){
                    options.add(transport);
                }
            }
            publicTransportOptions.put(station, options);
        }
    }

}
