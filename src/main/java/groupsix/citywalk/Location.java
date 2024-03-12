package groupsix.citywalk;

public class Location {
   // Variables for storing x and y coordinates
    private int x; // latitude
    private int y; // longitude

    //Constructor for Location Objects using x and y coordinates as arguments
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Method to return location coordinates as an array
    public int [] getLocation() {
        return new int[] {this.x, this.y};

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Method for calculating the manhattan distance between location and other location
    public int calDistance(Location other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
  
}
