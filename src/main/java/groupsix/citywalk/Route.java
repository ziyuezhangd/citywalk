package groupsix.citywalk;

import java.util.ArrayList;
import java.util.Arrays;

public class Route {
    private Station start;
    private Station end;
    private String mainTransport;
    private ArrayList<Leg> legs;
    private int carbonFP;
    private int time;
    private int distance;

    public Route(Station start, Station end, String mainTransport){
        this.start = start;
        this.end = end;
        switch (mainTransport){
            case "Walk":
                setWalk();
                break;
            case "Bike":
                setBike();
                break;
            case "Taxi":
                setTaxi();
                break;
        }
        calCarbonFP();
        calTime();
        calDistance();
    }
    public Route(Station start, Station end, String mainTransport, String[] transferStations, String[] transportLists){
        this.start = start;
        this.end = end;
        this.mainTransport = mainTransport;
        switch (mainTransport){
            case "Walk":
                setWalk();
                break;
            case "Bike":
                setBike();
                break;
            case "Taxi":
                setTaxi();
                break;
            case "Public":
                setPublic(transferStations, transportLists);
                break;
        }
        calCarbonFP();
        calTime();
        calDistance();
    }
    public int getModeNumber(){
        return legs.size();
    }
    public int getTime(){
        return time;
    }
    public int getDistance(){
        return distance;
    }
    public int getCarbonFP(){
        return carbonFP;
    }
    public Station getStart(){
        return start;
    }
    public Station getEnd(){
        return end;
    }
    private void setWalk(){
        TransportMode transport = City.getTransportByName("Walk");
        Leg walkLeg = new Leg(start, end, transport);
        addLeg(walkLeg);
    }
    private void setTaxi(){
        TransportMode transport = City.getTransportByName("Taxi");
        Leg taxiLeg = new Leg(start, end, transport);
        addLeg(taxiLeg);
    }
    private void setBike(){
        TransportMode bikeTransport = City.getTransportByName("Bike");
        TransportMode walkTransport = City.getTransportByName("Walk");
        Location bikeStart = start.nearestBikeLocation();
        Location bikeEnd = end.nearestBikeLocation();
        if (start.isSameLocation(bikeStart) && end.isSameLocation(bikeEnd)){
            Leg bikeLeg = new Leg(start, end, bikeTransport);
            addLeg(bikeLeg);
        } else if (start.isSameLocation(bikeStart)) {
            Leg bikeLeg = new Leg(start, bikeEnd, bikeTransport;
            Leg walkLeg = new Leg(bikeEnd, end, walkTransport);
            addLeg(bikeLeg);
            addLeg(walkLeg);
        } else if (end.isSameLocation(bikeEnd)) {
            Leg bikeLeg = new Leg(bikeStart, end, bikeTransport);
            Leg walkLeg = new Leg(start, bikeStart, walkTransport);
            addLeg(walkLeg);
            addLeg(bikeLeg);
        } else {
            Leg walkLegStart = new Leg(start, bikeStart, walkTransport);
            Leg walkLegEnd = new Leg(bikeEnd, end, walkTransport);
            Leg bikeLeg = new Leg(bikeStart, bikeEnd, bikeTransport);
            addLeg(walkLegStart);
            addLeg(bikeLeg);
            addLeg(walkLegEnd);
        }
    }

    private void setPublic(String[] transferStations, String[] transportLists){

    }

    private void addLeg(Leg leg){
        legs.add(leg);
    }

    private void calCarbonFP(){
        int carbonFP = 0;
        for (Leg leg: legs){
            carbonFP += leg.getCarbonFP();
        }
        this.carbonFP = carbonFP;
    }
    private void calTime(){
        int time = 0;
        for (Leg leg: legs){
            time += leg.getTime();
        }
        this.time = time;
    }
    private void calDistance(){
        int distance = 0;
        for (Leg leg: legs) {
            distance += leg.getDistance();
        }
        this.distance = distance;
    }

}
