package groupsix.citywalk;


public class TransportMode {
    private int time_factor;
    private double carbonFactor;
    private String name;

    public TransportMode(String name, int time_factor, double carbonFactor) {
        this.name = name;
        this.time_factor = time_factor;
        this.carbonFactor = carbonFactor;
    }

    public int getSpeed() {
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

    public String getType() {
        return name;
    }

    public void setType(String name) {
        this.name = name;
    }
}