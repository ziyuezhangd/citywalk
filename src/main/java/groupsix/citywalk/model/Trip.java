package groupsix.citywalk.model;

import groupsix.citywalk.model.Route;
import groupsix.citywalk.model.Station;

import java.util.ArrayList;

public class Trip {
    private final Station start;
    private final Station end;
    private Route selectedRoute;
    private ArrayList<TransportMode> transportTaken = new ArrayList<>();

    public Trip(Station start, Station end){
        this.start = start;
        this.end = end;
    }
    public ArrayList<Route> getRoutePlan(){
        ArrayList<Route> routePlan = new ArrayList<>();
        // Walk
        Route walkRoute = new Route(start, end, "Walk");
        routePlan.add(walkRoute);
        // Taxi
        Route taxiRoute = new Route(start, end, "Taxi");
        routePlan.add(taxiRoute);
        // Bike
        Route bikeRoute = new Route(start, end, "Bike");
        routePlan.add(bikeRoute);
        // Public Transport
        // Add direct route if exists
        ArrayList<PublicTransportMode> sameTransport = start.getSameTransport(end);
        if (!sameTransport.isEmpty()){
            for (PublicTransportMode transport: sameTransport) {
                Route directRoute = new Route(start, end, "Public");
                directRoute.setPublicRoute(transport);
                routePlan.add(directRoute);
            }
        }
        // Calculate transfer routes
        for (PublicTransportMode transportS: start.getPublicTransportList()) {
            // One-transfer route
            for (Station stationT: transportS.getTransferStations()){
                if (!stationT.equals(end)) {
                    ArrayList<PublicTransportMode> transferTransport = stationT.getSameTransport(end);
                    transferTransport.remove(transportS);
                    if (!transferTransport.isEmpty()) {
                        for (PublicTransportMode transportT: transferTransport) {
                            Route transferRoute = new Route(start, end, "Public");
                            transferRoute.setPublicRoute(transportS, stationT, transportT);
                            routePlan.add(transferRoute);
                        }
                    }
                }
            }
        }
        return routePlan;
    }
    public Route getSelectedRoute(){
        return selectedRoute;
    }
    public void selectRoute(int routeNo) {
        selectedRoute = getRoutePlan().get(routeNo);
        for (Leg leg: selectedRoute.getLegs()) {
            TransportMode transport = leg.getTransport();
            if (!transportTaken.contains(transport)) {
                transportTaken.add(transport);
            }
        }
    }
}
