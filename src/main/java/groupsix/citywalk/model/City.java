package groupsix.citywalk.model;

import groupsix.citywalk.util.MapConfig;

import java.util.HashMap;

public class City {
    private final int width;
    private final int height;
    private static HashMap<String, TransportMode> transportList = new HashMap<>();
    private static HashMap<String, PublicTransportMode> publicTransportList = new HashMap<>();
    private static HashMap<String, Station> stationList = new HashMap<>();

    public City(){
        //Initialise the city map
        width = MapConfig.mapSize[0];
        height = MapConfig.mapSize[1];
        initStations();
        initTransport();
    }
    //Helper static methods for other classes
    public static Station getStationByName(String name){
        return stationList.get(name);
    }
    public static TransportMode getTransportByName(String name){
        return transportList.get(name);
    }
    public static Station getStationByLocation(Location location){
        for (Station station: stationList.values()){
            if (station.isLocation(location)) {
                return station;
            }
        }
        return null;
    }

    public int[] getCitySize(){
        //Return the width and height of the map
        int[] shape = new int[2];
        shape[0] = width;
        shape[1] = height;
        return shape;
    }
    public static Location[] getBikeRegion(){
        BasicTransportMode bike = (BasicTransportMode) getTransportByName("Bike");
        return bike.getRegion();
    }
    private void initStations(){
        // Initialise all stations on the map
        for (int i=0; i<MapConfig.stationNames.length; i++){
            String name = MapConfig.stationNames[i];
            int x = MapConfig.stationLocations[i][0];
            int y = MapConfig.stationLocations[i][1];

            Station station = new Station(x, y, name, MapConfig.publicTransportOptions.get(name));
            stationList.put(name, station);
        }
    }
    private void initTransport(){
        //Initialise the transport mode on the map
        for (int i=0; i<MapConfig.allTransportNames.length; i++){
            String name = MapConfig.allTransportNames[i];
            int timeFactor = MapConfig.allTimeFactors[i];
            double carbonFactor = MapConfig.allCarbonFactors[i];
            TransportMode transport;
            if (i >= MapConfig.basicTransportNames.length) {
                transport = new PublicTransportMode(name, timeFactor, carbonFactor, MapConfig.publicTransportStops.get(name));
                publicTransportList.put(name, (PublicTransportMode) transport);
            } else{
                Location location1 = new Location(MapConfig.basicTransportRegion[i][0][0],MapConfig.basicTransportRegion[i][0][1]);
                Location location2 = new Location(MapConfig.basicTransportRegion[i][1][0],MapConfig.basicTransportRegion[i][1][1]);
                transport = new BasicTransportMode(name, timeFactor, carbonFactor, new Location[]{location1, location2});
            }
            transportList.put(name, transport);
        }
    }
}
