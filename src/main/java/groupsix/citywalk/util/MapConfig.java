package groupsix.citywalk.util;

import groupsix.citywalk.model.PublicTransportMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class MapConfig {
    //Define the size of city map
    public static final int[] mapSize = {10,10};

    //Define the stations
    //Station names
    public static final String[] stationNames = {"Dundrum","Rathmines","Blackrock","UCD","Dolphins Barn","Merrion",
            "Stephen's Green","Temple Bar","Trinity","Ballsbridge","The Spire","Sandymount","Docklands","Dublin Zoo",
            "Phoenix Park","IKEA","Howth"};
    //Station locations
    public static final int[][] stationLocations = {{8,0},{2,1},{10,2},{6,3},{0,3},{9,4},{4,4},{1,5},{3,6},{7,6},{5,7},
            {9,8},{7,9},{2,9},{1,10},{4,10},{10,10}};

    //Define the transport modes
    public static final String[] basicTransportNames = {"Walk", "Bike", "Taxi"};
    public static final String[] publicTransportNames = {"Bus", "Luas", "Dart"};
    public static final String[] allTransportNames = {"Walk", "Bike", "Taxi", "Bus", "Luas", "Dart"};
    public static final double[] allTimeFactors = {7.0,2.4,2.0,3.8,3.0,3.4};  //unit: minute
    public static final int[] allCarbonFactors = {2,4,48,30,15,18};  //unit: gram

    //Design basic transport regions
    public static final int[][][] basicTransportRegion = {{{0,0},{10,10}},{{1,4},{5,7}},{{0,0},{10,10}}};

    //Design public transport routes
    //Define the stops for each public transport mode
    public static final HashMap<String,ArrayList<String>> publicTransportStops;
    static {
        publicTransportStops = new HashMap<>();
        publicTransportStops.put("Bus", new ArrayList<>(Arrays.asList("Phoenix Park","Dublin Zoo","Trinity","The Spire",
                "Stephen's Green","UCD","Blackrock","Dundrum")));
        publicTransportStops.put("Luas", new ArrayList<>(Arrays.asList("Dolphins Barn","Temple Bar","Trinity",
                "Stephen's Green","The Spire","Ballsbridge","Docklands","Sandymount")));
        publicTransportStops.put("Dart", new ArrayList<>(Arrays.asList("Howth","Sandymount","Merrion","Blackrock",
                "Dundrum","Rathmines","Dolphins Barn","Dublin Zoo","Phoenix Park")));
    }
    //Compute the transport options for each station
    public static final HashMap<String, ArrayList<String>> publicTransportOptions;
    static {
        publicTransportOptions = new HashMap<>();
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
