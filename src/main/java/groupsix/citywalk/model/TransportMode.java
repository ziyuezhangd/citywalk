package groupsix.citywalk.model;


public abstract class TransportMode {
    private int timeFactor;
    private double carbonFactor;
    private String name;

    public TransportMode(String name, int timeFactor, double carbonFactor) {
        this.name = name;
        this.timeFactor = timeFactor;
        this.carbonFactor = carbonFactor;
    }

    public int getTimeFactor() {
        return timeFactor;
    }

    public void setTimeFactor(int timeFactor) {
        this.timeFactor = timeFactor;
    }

    public double getCarbonFactor() {
        return carbonFactor;
    }

    public void setCarbonFactor(double carbonFactor) {
        this.carbonFactor = carbonFactor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean checkLocation (Location location);

}