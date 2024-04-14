package groupsix.citywalk.model;

import java.util.ArrayList;

public class Route {
    private final Station start;
    private final Station end;
    private final String mainTransport;
    private ArrayList<Leg> legs = new ArrayList<>();

    public Route(Station start, Station end, String mainTransport){
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
        }
    }

    public int getModeNumber(){
        return legs.size();
    }
    public int getCarbonFP(){
        int carbonFP = 0;
        for (Leg leg: legs){
            carbonFP += leg.getCarbonFP();
        }
        return carbonFP;
    }
    public int getTime(){
        int time = 0;
        for (Leg leg: legs){
            time += leg.getTime();
        }
        switch (mainTransport){
            case "Bike":
                time += 4;  // Time to borrow and return a bike
                break;
            case "Taxi":
                time += 7;  // Time to wait for a taxi
                break;
            case "Public":
                time += getModeNumber() * 5;  // Time to wait for public transports
        }
        return time;
    }
    public int getDistance(){
        int distance = 0;
        for (Leg leg: legs) {
            distance += leg.getDistance();
        }
        return distance;
    }
    public Station getStart(){
        return start;
    }
    public Station getEnd(){
        return end;
    }
    public ArrayList<Leg> getLegs() {
        return legs;
    }

    private void setWalk(){
        TransportMode transport = City.getTransportByName("Walk");
        Leg walkLeg = new Leg(start, end, transport);
        legs.add(walkLeg);
    }
    private void setTaxi(){
        TransportMode transport = City.getTransportByName("Taxi");
        Leg taxiLeg = new Leg(start, end, transport);
        legs.add(taxiLeg);
    }
    private void setBike(){
        TransportMode bikeTransport = City.getTransportByName("Bike");
        TransportMode walkTransport = City.getTransportByName("Walk");
        Location bikeStart = start.nearestBikeLocation();
        Location bikeEnd = end.nearestBikeLocation();
        if (start.equals(bikeStart) && end.equals(bikeEnd)){
            Leg bikeLeg = new Leg(start, end, bikeTransport);
            legs.add(bikeLeg);
        } else if (start.equals(bikeStart)) {
            Leg bikeLeg = new Leg(start, bikeEnd, bikeTransport);
            Leg walkLeg = new Leg(bikeEnd, end, walkTransport);
            legs.add(bikeLeg);
            legs.add(walkLeg);
        } else if (end.equals(bikeEnd)) {
            Leg bikeLeg = new Leg(bikeStart, end, bikeTransport);
            Leg walkLeg = new Leg(start, bikeStart, walkTransport);
            legs.add(walkLeg);
            legs.add(bikeLeg);
        } else {
            Leg walkLegStart = new Leg(start, bikeStart, walkTransport);
            Leg walkLegEnd = new Leg(bikeEnd, end, walkTransport);
            Leg bikeLeg = new Leg(bikeStart, bikeEnd, bikeTransport);
            legs.add(walkLegStart);
            legs.add(bikeLeg);
            legs.add(walkLegEnd);
        }
    }

    public void setPublicRoute(PublicTransportMode transport){
        Leg directPublicLeg = new Leg(start, end, transport);
        legs.add(directPublicLeg);
    }
    public void setPublicRoute(PublicTransportMode transportS, Station transfer, PublicTransportMode transportE){
        Leg startToTransferLeg = new Leg(start, transfer, transportS);
        Leg transferToEndLeg = new Leg(transfer, end, transportE);
        legs.add(startToTransferLeg);
        legs.add(transferToEndLeg);
    }

    @Override
    public String toString() {
        String description = "";
        for (Leg leg: legs) {
            Station stationStart = City.getStationByLocation(leg.getStart());
            if (stationStart != null) {
                description += stationStart.getStationName() + " > " + leg.toString() + " > ";
            } else {
                description += "BikeStation@" + leg.getStart().toString() + " > " + leg.toString() + " > ";
            }
            if (end.isLocation(leg.getEnd())) {
                description += end.getStationName();
            }
        }
        description += "\n" + getTime() + "min\n" + getCarbonFP() + "g CO2 footprint";
        return description;
    }
}
