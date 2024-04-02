package groupsix.citywalk.model;

import groupsix.citywalk.model.Route;
import groupsix.citywalk.model.Station;

import java.util.ArrayList;

public class Trip {
    private final Station start;
    private final Station end;
    private ArrayList<Route> routePlan;

    public Trip(Station start, Station end){
        this.start = start;
        this.end = end;
        calRoutePlan();
    }
    public ArrayList<Route> getRoutePlan(){
        return routePlan;
    }

    private void calRoutePlan(){
        //Walk
        Route walkRoute = new Route(start, end, "Walk");
        addRoute(walkRoute);
        //Taxi
        Route taxiRoute = new Route(start, end, "Taxi");
        addRoute(taxiRoute);
        //Bike
        Route bikeRoute = new Route(start, end, "Bike");
        addRoute(bikeRoute);
        //Public Transport
        for (Route route: calPublicRoutes()){
            addRoute(route);
        }
    }
    private ArrayList<Route> calPublicRoutes(){
        //Calculate possible public transport routes
        ArrayList<String> transferFromStart;
        ArrayList<String> transferFromEnd;
        for (String transportStartNames: start.getPublicTransportList()) {
            Leg legFromStart = new Leg();
            legFromStart.setStart(start);
            //to be continued
        }
        return null;
    }

    private void addRoute(Route route){
        routePlan.add(route);
    }
}
