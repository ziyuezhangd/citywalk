package groupsix.citywalk.model;

public class Leg {
    private Location start;
    private Location end;
    private TransportMode transport;

    public Leg(Location start, Location end, TransportMode transport) {
        this.start = start;
        this.end = end;
        this.transport = transport;
    }

    public int getDistance(){
        return start.calDistance(end);
    }
    public int getTime(){
        int distance = getDistance();
        return distance * transport.getSpeed();
    }
    public int getCarbonFP(){
        int distance = getDistance();
        return (int) (distance * transport.getCarbonFactor());
    }
    public TransportMode getTransport(){
        return transport;
    }
}
