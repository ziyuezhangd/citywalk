package groupsix.citywalk.model;


public abstract class TransportMode {
    private final double timeFactor;
    private final int carbonFactor;
    private final String name;

    public TransportMode(String name, double timeFactor, int carbonFactor) {
        this.name = name;
        this.timeFactor = timeFactor;
        this.carbonFactor = carbonFactor;
    }

    public double getTimeFactor() {
        return timeFactor;
    }

    public int getCarbonFactor() {
        return carbonFactor;
    }

    public String getName() {
        return name;
    }

    public abstract boolean checkLocation (Location location);

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransportMode transport = (TransportMode) obj;
        return name.equals(transport.getName());
    }
}