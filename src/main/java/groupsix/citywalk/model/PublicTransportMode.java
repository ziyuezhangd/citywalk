package groupsix.citywalk.model;
import groupsix.citywalk.util.MapConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublicTransportMode extends TransportMode {
    private String[] stationList;

    public PublicTransportMode(String name, int timeFactor, double carbonFactor, String[] stationList) {
        super(name, timeFactor, carbonFactor);
        this.stationList = stationList;
    }
    public String[] listStations() {
        return stationList;
    }

    public boolean checkLocation(Location location) {
        for (String stationName : stationList) {
            if (City.getStationByName(stationName).isLocation(location)) {
                return true;
            }
        }
        return false;
    }

}

