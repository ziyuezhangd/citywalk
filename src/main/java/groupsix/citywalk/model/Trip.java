package groupsix.citywalk.model;

import groupsix.citywalk.model.Route;
import groupsix.citywalk.model.Station;

import java.util.ArrayList;

public class Trip {
    private final Station start;
    private final Station end;
    private ArrayList<Route> routePlan = new ArrayList<>();
    private Route selectedRoute;
    private ArrayList<TransportMode> transportTaken = new ArrayList<>();

    public Trip(Station start, Station end){
        this.start = start;
        this.end = end;
        calRoutePlan();
    }
    public ArrayList<Route> getRoutePlan(){
        return routePlan;
    }
    public Route getSelectedRoute(){
        return selectedRoute;
    }
    public void selectRoute(Route route) {
        selectedRoute = route;
        for (Leg leg: route.getLegs()) {
            TransportMode transport = leg.getTransport();
            if (!transportTaken.contains(transport)) {
                transportTaken.add(transport);
            }
        }
    }
    private void calRoutePlan(){
        // Walk
        Route walkRoute = new Route(start, end, "Walk");
        addRoute(walkRoute);
        // Taxi
        Route taxiRoute = new Route(start, end, "Taxi");
        addRoute(taxiRoute);
        // Bike
        Route bikeRoute = new Route(start, end, "Bike");
        addRoute(bikeRoute);
        // Public Transport
        calPublicRoutes();
    }
    private void calPublicRoutes(){

        // Add direct route if exists
        ArrayList<PublicTransportMode> sameTransport = start.getSameTransport(end);
        if (!sameTransport.isEmpty()){
            for (PublicTransportMode transport: sameTransport) {
                Route directRoute = new Route(start, end, "Public");
                directRoute.setPublicRoute(transport);
                addRoute(directRoute);
            }
        }
        // Calculate transfer routes
        for (PublicTransportMode transportS: start.getPublicTransportList()) {
            // One-transfer route
            for (Station stationT: transportS.getTransferStations()){
                ArrayList<PublicTransportMode> transferTransport = stationT.getSameTransport(end);
                transferTransport.remove(transportS);
                if (!transferTransport.isEmpty()) {
                    for (PublicTransportMode transportT: transferTransport) {
                        Route transferRoute = new Route(start, end, "Public");
                        transferRoute.setPublicRoute(transportS, stationT, transportT);
                        addRoute(transferRoute);
                    }
                }
            }
        }
    }

    private void addRoute(Route route){
        routePlan.add(route);
    }
}
