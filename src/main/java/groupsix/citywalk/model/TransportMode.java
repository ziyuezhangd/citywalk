package groupsix.citywalk.model;


public abstract class TransportMode {
    private int time_factor;
    private double carbonFactor;
    private String name;

    public TransportMode(String name, int time_factor, double carbonFactor) {
        this.name = name;
        this.time_factor = time_factor;
        this.carbonFactor = carbonFactor;
    }

    public int getTime_factor() {
        return time_factor;
    }


    public void setSpeed(int time_factor) {
        this.time_factor = time_factor;
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