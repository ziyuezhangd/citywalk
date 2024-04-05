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

        for (PublicTransportMode transport: transportS){
            // Add direct routes
            if (transportE.contains(transport)){
                Route directRoute = new Route(start, end, "Public");
                directRoute.setPublicRoute(transport);
                addRoute(directRoute);
            }
            // Calculate transfer routes
            ArrayList<Station> stopsS = transport.getStations();
            for (Station stop: stopsS){
                if (stop.checkTransfer(start, end)){
                    for (PublicTransportMode transferTransport: stop.getOtherTransport(transport)){
                        // Calculate one-transfer routes
                        if (transportE.contains(transferTransport)){
                            Route oneTransferStart = new Route(start, end, "Public");
                            oneTransferStart.setPublicRoute(transport, stop, transferTransport);
                            addRoute(oneTransferStart);
                        }
                    }
                }
            }
        }
    }

    private void addRoute(Route route){
        routePlan.add(route);
    }
}
