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
        super(x, y);
        this.stationName = stationName;
        String[] transportNameList = MapConfig.transportOptions.get(stationName);
        transportList = new TransportMode[transportNameList.length];
        for (String transport : transportNameList) {
            int i = 0;
            transportList[i] = City.getTransportByName(transportNameList[i]);
        }
        Station.allStations.put(stationName, this);
    }

    public Location nearestBikeLocation() {
        //define bike area
        Location[] bikeArea = City.getBikeRegion();
        int bikeAreaStartX = Math.min(bikeArea[0].getX(), bikeArea[1].getX());
        int bikeAreaEndX = Math.max(bikeArea[0].getX(), bikeArea[1].getX());
        int bikeAreaStartY = Math.min(bikeArea[0].getY(), bikeArea[1].getY());
        int bikeAreaEndY = Math.max(bikeArea[0].getY(), bikeArea[1].getY());

        if (this.getX() >= bikeAreaStartX && this.getX() <= bikeAreaEndX && this.getY() >= bikeAreaStartY
                && this.getY() <= bikeAreaEndY) {
            // Station is within the bike area
            return this; // This station is the nearest bike station since it's in the bike zone
        } else {
            //checks if the location is within the station range
            int zoneX = (this.getX() < bikeAreaStartX) ? 0 : (this.getX() > bikeAreaEndX) ? 2 : 1;
            int zoneY = (this.getY() < bikeAreaStartY) ? 0 : (this.getY() > bikeAreaEndY) ? 2 : 1;
            int targetX;
            int targetY;
            if (zoneX != 1 && zoneY != 1) {
                // Zone 3 (corners)
                // Get the corner location in the bike zone closest to the station
                targetX = (zoneX == 0) ? bikeAreaStartX : bikeAreaEndX;
                targetY = (zoneY == 0) ? bikeAreaStartY : bikeAreaEndY;
            } else {
                // Zone 2 (same x- or y-axis as the bike area)
                targetX = (zoneX == 1) ? this.getX() : (zoneX == 0) ? bikeAreaStartX : bikeAreaEndX;
                // If the station is on the same Y axis as the bike area, its Y coordinate will be within the bike area's Y bounds
                targetY = (zoneY == 1) ? this.getY() : (zoneY == 0) ? bikeAreaStartY : bikeAreaEndY;
            }
            return new Location(targetX, targetY);
        }
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

    public boolean isLocation(Location location) {
        return getX() == location.getX() && getY() == location.getY();
    }

}