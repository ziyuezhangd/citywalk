package groupsix.citywalk.util;

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

    //Define the bicycle region
    public static final int[][] bicycleLocations = {{1,4},{5,7}};

    //Define the transport modes
    public static final String[] basicTransportNames = {"Walk", "Bike", "Taxi"};
    public static final String[] publicTransportNames = {"Bus", "Luas", "Dart"};
    public static final String[] allTransportNames = {"Walk", "Bike", "Taxi", "Bus", "Luas", "Dart"};
    public static final int[] allTimeFactors = {5,4,3,2,1};
    public static final double[] allCarbonFactors = {1,1.1,1.2,1.3,1.4};

    //Design public transport routes
    //Define the stops for each public transport mode
    public static final HashMap<String,String[]> transportStops;
    static {
        transportStops = new HashMap<>();
        transportStops.put("Bus", new String[]{"UCD", "Dundrum"});
        transportStops.put("Luas", new String[]{"Trinity", "Dundrum"});
    }
    //Compute the transport options for each station
    public static final HashMap<String,String[]> transportOptions;
    static {
        transportOptions = new HashMap<>();
        for (String station: stationNames){
            ArrayList<String> temp_list = new ArrayList<>();
            for (String transport: publicTransportNames){
                if (Arrays.asList(transportStops.get(transport)).contains(station)){
                    temp_list.add(transport);
                }
            }
            String[] temp_array = temp_list.toArray(new String[temp_list.size()]);
            transportOptions.put(station, temp_array);
        }
    }
}
