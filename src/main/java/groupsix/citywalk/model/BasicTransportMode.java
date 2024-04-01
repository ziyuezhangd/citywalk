package groupsix.citywalk.model;

import groupsix.citywalk.util.MapConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicTransportMode extends TransportMode{
    private ArrayList<Location> region;

    public BasicTransportMode(String name, int timeFactor, double carbonFactor, ArrayList<Location> region) {
        super(name, timeFactor, carbonFactor);
        this.region = region;
    }

    public boolean checkLocation (Location location){
        for (Location loc : region) {
            if (loc.equals(location)) {
                return true;
            }
        }
        return false;
    }
}
