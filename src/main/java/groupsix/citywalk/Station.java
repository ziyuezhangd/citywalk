package groupsix.citywalk;
import java.util.ArrayList;
import java.util.HashMap;
public class Station extends Location {
    private String stationName;
    private TransportMode[] transportList;
    // A static HashMap to keep track of all stations created, accessible to all Station instances.
    private static HashMap<String, Station> allStations = new HashMap<>();
    //Constructor
    public Station(int x, int y, String stationName, TransportMode[] transportList) {
        super(x,y);
        this.stationName = stationName;
        this.transportList = transportList;
        // add stations to the allStations map
        MapConfig.addAllStations(this.stationName, this);
        allStations.put(stationName, this);  // Adds this station to the allStations map with the stationName as the key.
    }

    public Location nearestBikeStation() {
        Location nearestBikeStation = null;
        int nearestDistance = Integer.MAX_VALUE;

        for (int[] bikeLoc : MapConfig.bicycleLocs) {
            Location bikeStationLocation = new Location(bikeLoc[0], bikeLoc[1]);
            int distance = this.calDistance(bikeStationLocation);

            if (distance < nearestDistance) {
                nearestBikeStation = bikeStationLocation;
                nearestDistance = distance;
            }
        }

        return nearestBikeStation;
    }

    // Method to get names of all public transport modes supported by the current station
    public ArrayList<String> getPublicTransportList() {
        ArrayList<String> publicTransportList = new ArrayList<>();
        // Check if the current station is in the transportOptions map
        if (MapConfig.transportOptions.containsKey(this.stationName)) {
            // If it is, get the array of transport modes for this station
            String[] transports = MapConfig.transportOptions.get(this.stationName);
            // Convert the array to an ArrayList and return it
            publicTransportList.addAll(Arrays.asList(transports));
        }

        return publicTransportList;
    }

    public boolean checkTransfer(Station start, Station end) {
        // Get the transport options for the start and end stations from the MapConfig
        ArrayList<String> startTransports = MapConfig.transportOptions.get(start.getStationName());
        ArrayList<String> endTransports = MapConfig.transportOptions.get(end.getStationName());
        ArrayList<String> thisTransports = MapConfig.transportOptions.get(this.getStationName());

        // Check if there's at least one common transport mode between start station and this (transfer) station
        for (String transport : thisTransports) {
            if (startTransports.contains(transport) && endTransports.contains(transport)) {
                return true;
            }
        }

        return false;
    }
    public String getStationName() {
        return stationName;
    }

    public TransportMode[] getTransportList() {
        return transportList;
    }

    // Setters
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setTransportList(TransportMode[] transportList) {
        this.transportList = transportList;
    }

}
