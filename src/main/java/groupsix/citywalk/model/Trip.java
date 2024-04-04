package groupsix.citywalk.model;

import groupsix.citywalk.model.Route;
import groupsix.citywalk.model.Station;

import java.util.ArrayList;

public class Trip {
    private final Station start;
    private final Station end;
    private ArrayList<Route> routePlan = new ArrayList<>();

    public Trip(Station start, Station end){
        this.start = start;
        this.end = end;
        calRoutePlan();
    }
    public ArrayList<Route> getRoutePlan(){
        return routePlan;
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
        // Calculate possible public transport routes
        ArrayList<PublicTransportMode> transportS = start.getPublicTransportList();
        ArrayList<PublicTransportMode> transportE = end.getPublicTransportList();
        // Add direct routes
        for (PublicTransportMode transport: transportS){
            if (transportE.contains(transport)){
                Route directRoute = new Route(start, end, "Public");
                directRoute.setPublicRoute(transport);
                addRoute(directRoute);
            }
        }
        // Calculate one-transfer routes from start
        for (PublicTransportMode transport: transportS){
            ArrayList<Station> stops = transport.getStations();
            for (Station stop: stops){
                if (stop.checkTransfer(start, end)){
                    //检查它的交通方式是否有与end一致的
                }
            }
        }
        // Calculate one-transfer routes from end
        // Calculate two-transfer routes
    }

    private void addRoute(Route route){
        routePlan.add(route);
    }
}
