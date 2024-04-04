package groupsix.citywalk.model;
import groupsix.citywalk.util.MapConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PublicTransportMode extends TransportMode {
    private ArrayList<Station> stationList;

    public PublicTransportMode(String name, int timeFactor, double carbonFactor) {
        super(name, timeFactor, carbonFactor);
    }
    public ArrayList<Station> getStations() {
        return stationList;
    }

    public boolean checkLocation(Location location) {
        for (Station station: stationList) {
            if (station.isLocation(location)) {
                return true;
            }
        }
        return false;
    }

    public void setStationList(ArrayList<Station> stations) {
        this.stationList = stations;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PublicTransportMode transport = (PublicTransportMode) obj;
        return this.getName().equals(transport.getName());
    }
}

