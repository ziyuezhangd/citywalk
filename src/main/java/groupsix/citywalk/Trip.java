package groupsix.citywalk;

import java.util.ArrayList;

public class Trip {
    private Station start;
    private Station end;
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
        return null;
    }

    private void addRoute(Route route){
        routePlan.add(route);
    }
}
