package groupsix.citywalk;

import java.util.HashMap;

public class Map {
    private final int width;
    private final int height;
    private Location[] bicycleRegion = new Location[2];
    private HashMap<String, TransportMode> transportList = new HashMap<>();
    private HashMap<String, PublicTransportMode> publicList = new HashMap<>();
    private HashMap<String, Station> stationList = new HashMap<>();


    public Map(int w, int h){
        width = w;
        height = h;
    }

    public int[] getMap(){
        //Return the width and height of the map
        int[] shape = new int[2];
        shape[0] = width;
        shape[1] = height;
        return shape;
    }

    public void setBicycleRegion(Location l1, Location l2){
        bicycleRegion[0] = l1;
        bicycleRegion[1] = l2;
    }
    public Location[] getBicycleRegion(){
        return bicycleRegion;
    }

    public void initStations(){
        // Initialise all stations on the map
        MapConfig mapConfig = new MapConfig();
        for (int i=0; i<mapConfig.stationNames.length; i++){
            String name = mapConfig.stationNames[i];
            int x = mapConfig.stationXs[i];
            int y = mapConfig.stationYs[i];
            Station station = new Station(name, x, y);
            stationList.put(name, station);
        }

    }
    public void initTransport(){

    }

    public void initPublicTransport(){

    }

}
