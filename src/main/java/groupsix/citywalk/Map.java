package groupsix.citywalk;

import java.util.HashMap;

public class Map {
    private final int width;
    private final int height;
    private Location[] bicycleRegion = new Location[2];
    private HashMap<String, TransportMode> transportList;
    private HashMap<String, PublicTransportMode> publicList;
    private HashMap<String, Station> stationList;


    public Map(int w, int h){
        width = w;
        height = h;
    }

    //Return the width and height of the map
    public int[] getMap(){
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





}
