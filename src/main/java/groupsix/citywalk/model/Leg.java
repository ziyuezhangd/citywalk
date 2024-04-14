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
    public Location getStart() {
        return start;
    }
    public Location getEnd(){
        return end;
    }
    public void setTransport(TransportMode transport){
        this.transport = transport;
    }
    public int getDistance(){
        return start.calDistance(end);
    }
    public int getTime(){
        int distance = getDistance();
        return (int) (distance * transport.getTimeFactor());
    }
    public int getCarbonFP(){
        int distance = getDistance();
        return distance * transport.getCarbonFactor();
    }
    public TransportMode getTransport(){
        return transport;
    }

    @Override
    public String toString() {
        String description = "";
        switch (transport.getName()) {
            case "Walk" -> description += "\uD83D\uDC63";
            case "Taxi" -> description += "\uD83D\uDE96";
            case "Bike" -> description += "\uD83D\uDEB2";
            case "Bus" -> description += "\uD83D\uDE8D";
            case "Luas" -> description += "\uD83D\uDE8A";
            case "Dart" -> description += "\uD83D\uDE89";
        }
        return description;
    }
}
