package groupsix.citywalk;

import java.util.HashMap;

public class City {
    private final int width;
    private final int height;
    private static final MapConfig mapConfig = new MapConfig();
    private final Location[] bicycleRegion = new Location[2];
    private HashMap<String, TransportMode> transportList = new HashMap<>();
    private HashMap<String, PublicTransportMode> publicList = new HashMap<>();
    private HashMap<String, Station> stationList = new HashMap<>();


    public City(){
        //Initialise the city map
        width = mapConfig.mapSize[0];
        height = mapConfig.mapSize[1];
        initBicycleRegion();
        initStations();
        initTransport();
        initPublicTransport();
    }

    public int[] getCitySize(){
        //Return the width and height of the map
        int[] shape = new int[2];
        shape[0] = width;
        shape[1] = height;
        return shape;
    }

    public Location[] getBicycleRegion(){
        return bicycleRegion;
    }

    public void initStations(){
        // Initialise all stations on the map
        for (int i=0; i<mapConfig.stationNames.length; i++){
            String name = mapConfig.stationNames[i];
            int x = mapConfig.stationLocs[i][0];
            int y = mapConfig.stationLocs[i][1];
            Station station = new Station(name, x, y);
            stationList.put(name, station);
        }

    }
    public void initBicycleRegion(){
        //Initialise the bicycle region on the map
        bicycleRegion[0] = new Location(mapConfig.bicycleLocs[0][0],mapConfig.bicycleLocs[0][1]);
        bicycleRegion[1] = new Location(mapConfig.bicycleLocs[1][0],mapConfig.bicycleLocs[1][1]);
    }
    public void initTransport(){

    }

    public void initPublicTransport(){

    }

}
