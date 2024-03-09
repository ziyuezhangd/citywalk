package groupsix.citywalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class MapConfig {
    //Define the size of city map
    static final int[] mapSize = {10,10};

    //Define the stations
    static final String[] stationNames = {"UCD", "Trinity", "Dundrum"};
    static final int[][] stationLocs = {{1,2}, {3,4}, {5,6}};

    //Define the bicycle region
    static final int[][] bicycleLocs = {{1,2},{3,4}};

    //Define the transport modes
    static final String[] basicTransportNames = {"Walk", "Bike", "Taxi"};
    static final String[] publicTransportNames = {"Bus", "Luas"};
    static final HashMap<String,String[]> transportStops;
    static {
        transportStops = new HashMap<>();
        transportStops.put("Bus", new String[]{"UCD", "Dundrum"});
        transportStops.put("Luas", new String[]{"Trinity", "Dundrum"});
    }

    //Compute the transport options for each station
    static final HashMap<String,String[]> transportOptions;
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
