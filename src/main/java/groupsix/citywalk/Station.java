package groupsix.citywalk;
import java.util.ArrayList;
import java.util.HashMap;
public class Station extends Location {
    private String stationName;
    private TransportMode[] transportList;
    // A static HashMap to keep track of all stations created, accessible to all Station instances.
    private static HashMap<String, Station> allStations = new HashMap<>();
    //Constructor
    public Station(int x, int y, String stationName) {
        super(x,y);
        this.stationName = stationName;
        String [] transportNameList = MapConfig.transportOptions.get(stationName);
        transportList = new TransportMode[transportNameList.length];
        for (String transport: transportNameList){
            int i = 0;
            transportList[i] = City.getTransportByName(transportNameList[i]);
        }
        Station.allStations.put(stationName, this);
    }

    public Location nearestBikeStationInBikeZone() {
        //define bike area
        int bikeAreaStartX = 1;
        int bikeAreaEndX = 3;
        int bikeAreaStartY = 1;
        int bikeAreaEndY = 3;

        if (this.getX() >= bikeAreaStartX && this.getX() <= bikeAreaEndX &&
                this.getY() >= bikeAreaStartY && this.getY() <= bikeAreaEndY) {
            // Station is within the bike area
            return this; // This station is the nearest bike station since it's in the bike zone
        }
        //checks if the location is within the station range
        int zoneX = (this.getX() < bikeAreaStartX) ? 0 : (this.getX() > bikeAreaEndX) ? 2 : 1;
        int zoneY = (this.getY() < bikeAreaStartY) ? 0 : (this.getY() > bikeAreaEndY) ? 2 : 1;

        // Zone 3 (corners)
        if (zoneX != 1 && zoneY != 1) {
            // Get the corner location in the bike zone closest to the station
            int targetX = (zoneX == 0) ? bikeAreaStartX : bikeAreaEndX;
            int targetY = (zoneY == 0) ? bikeAreaStartY : bikeAreaEndY;
            return new Location(targetX, targetY);
        }

        // Zone 2 (same x or y axis as the bike area)
        if (zoneX == 1 || zoneY == 1) {
            int targetX = (zoneX == 1) ? this.getX() : (zoneY == 0) ? bikeAreaStartX : bikeAreaEndX;

            // If the station is on the same Y axis as the bike area, its Y coordinate will be within the bike area's Y bounds
            int targetY = (zoneY == 1) ? this.getY() : (zoneX == 0) ? bikeAreaStartY : bikeAreaEndY;

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
