package groupsix.citywalk.model;
import groupsix.citywalk.util.MapConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublicTransportMode extends TransportMode {
    private static HashMap<String, Station> stations = new HashMap<>();

    public PublicTransportMode(String name, int timeFactor, double carbonFactor, HashMap<String, Station> stations) {
        super(name, timeFactor, carbonFactor);
        this.stations = stations;
    }
    public List<Station> listStations() {
        return new ArrayList<>(stations.values());
    }

    public boolean checkLocation(Location location) {
        for (Station station : stations.values()) {
            if (station.isLocation(location)) {
                return true;
            }
        }
        return false;

    }
    public void showStationsList() {
        System.out.println("Stations List for " + this.getName() + ":");
        for (Station station : stations.values()) {
            System.out.println(station.getStationName());
        }
    }

}

