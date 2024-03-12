package groupsix.citywalk;

import java.util.Arrays;
import java.util.HashMap;

public class City {
    private final int width;
    private final int height;
    private final Location[] bicycleRegion = new Location[2];
    private static HashMap<String, TransportMode> transportList = new HashMap<>();
    private static HashMap<String, PublicTransportMode> publicTransportList = new HashMap<>();
    private static HashMap<String, Station> stationList = new HashMap<>();

    public City(){
        //Initialise the city map
        width = MapConfig.mapSize[0];
        height = MapConfig.mapSize[1];
        initBicycleRegion();
        initStations();
        initTransport();
    }
    public static Station getStationByName(String name){
        return stationList.get(name);
    }
    public static TransportMode getTransportByName(String name){
        return transportList.get(name);
    }
    public static Station getStationByLocation(Location location){
        ;
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
        for (int i=0; i<MapConfig.stationNames.length; i++){
            String name = MapConfig.stationNames[i];
            int x = MapConfig.stationLocations[i][0];
            int y = MapConfig.stationLocations[i][1];
            Station station = new Station(name, x, y);
            stationList.put(name, station);
        }

    }
    public void initBicycleRegion(){
        //Initialise the bicycle region on the map
        bicycleRegion[0] = new Location(MapConfig.bicycleLocations[0][0],MapConfig.bicycleLocations[0][1]);
        bicycleRegion[1] = new Location(MapConfig.bicycleLocations[1][0],MapConfig.bicycleLocations[1][1]);
    }
    public void initTransport(){
        //Initialise the transport mode on the map
        for (int i=0; i<MapConfig.allTransportNames.length; i++){
            String name = MapConfig.allTransportNames[i];
            int timeFactor = MapConfig.allTimeFactors[i];
            double carbonFactor = MapConfig.allCarbonFactors[i];
            TransportMode transport;
            if (i>=MapConfig.basicTransportNames.length){
                transport = new PublicTransportMode(name, timeFactor, carbonFactor);
                publicTransportList.put(name, transport);
            } else{
                transport = new TransportMode(name, timeFactor, carbonFactor);
            }
            transportList.put(name, transport);
        }
    }



}
