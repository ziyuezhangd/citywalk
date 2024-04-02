package groupsix.citywalk.model;

public class Leg {
    private Location start;
    private Location end;
    private TransportMode transport;

    public Leg(){}
    public Leg(Location start, Location end, TransportMode transport) {
        this.start = start;
        this.end = end;
        this.transport = transport;
    }
    public void setStart(Location start) {
        this.start = start;
    }
    public void setEnd(Location end){
        this.end = end;
    }
    public void setTransport(TransportMode transport){
        this.transport = transport;
    }
    public int getDistance(){
        return start.calDistance(end);
    }
    public int getTime(){
        int distance = getDistance();
        return distance * transport.getTimeFactor();
    }
    public int getCarbonFP(){
        int distance = getDistance();
        return (int) (distance * transport.getCarbonFactor());
    }
    public TransportMode getTransport(){
        return transport;
    }
}
