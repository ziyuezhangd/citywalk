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
    public static final int[] allTimeFactors = {5,4,3,2,1};
    public static final double[] allCarbonFactors = {1,1.1,1.2,1.3,1.4};

    //Design basic transport regions
    public static final int[][][] basicTransportRegion = {{{0,0},{10,10}},{{1,4},{5,7}},{{0,0},{10,10}}};

    //Design public transport routes
    //Define the stops for each public transport mode
    public static final HashMap<String,String[]> publicTransportStops;
    static {
        publicTransportStops = new HashMap<>();
        publicTransportStops.put("Bus", new String[]{"UCD", "Dundrum"});
        publicTransportStops.put("Luas", new String[]{"Trinity", "Dundrum"});
    }
    //Compute the transport options for each station
    public static final HashMap<String, String[]> publicTransportOptions;
    static {
        publicTransportOptions = new HashMap<>();
        for (String station: stationNames){
            ArrayList<String> tempList = new ArrayList<>();
            for (String transport: publicTransportNames){
                if (Arrays.asList(publicTransportStops.get(transport)).contains(station)){
                    tempList.add(transport);
                }
            }
            String[] tempArray = tempList.toArray(new String[tempList.size()]);
            publicTransportOptions.put(station, tempArray);
        }
    }
}
