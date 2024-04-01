package groupsix.citywalk.model;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int carbonFP;
    private int gemCollect;
    private ArrayList<Route> levelSelectedRoutes = new ArrayList<>();
    private ArrayList<ArrayList<Route>> gameSelectedRoutes = new ArrayList<>();

    // Time the user has spent in the current level
    private int timeSpent;
    private Location location;
    private int levelScore;
    private int scoreSum;

    // Constructor
    public Player(String playerName, Station location) {
        this.playerName = playerName;
        this.carbonFP = 0;
        this.gemCollect = 0;
        this.timeSpent = 0;
        this.location = location;
        //Initialize score
        this.levelScore = 0;
        this.scoreSum = 0;
    }

    public String getPlayerName() {
        return playerName;
    }
    // Update time and carbonFP after each trip
    public void finishTrip(Route route) {
        timeSpent += route.getTime();
        carbonFP += route.getCarbonFP();
        location = route.getEnd();
    }
    public void startNewLevel(){
        this.carbonFP = 0;
        this.gemCollect = 0;
        this.timeSpent = 0;
        gameSelectedRoutes.add(levelSelectedRoutes);
        levelSelectedRoutes = new ArrayList<>();
    }
    // Get player's carbonFP
    public int getCarbonFP() {
        return carbonFP;
    }

    // Store the routes taken and for each trip in one level
    public void routeSelect(Route route) {
        levelSelectedRoutes.add(route);
        location = route.getEnd();
    }

    public int gemCollect(){
        gemCollect += 1;
        return gemCollect;
    }

    public Location getLocation(){
        return location;
    }

    public int getTimeSpent(){
        return timeSpent;
    }

    public int getGemCollect(){
        return gemCollect;
    }

    public void calScore() {
        this.levelScore = (int) (Math.log(1000 - (1 * Math.log(timeSpent)) - (3 * Math.log(carbonFP))) * 10);
        // Update total score
        this.scoreSum += this.levelScore;
        // Reset level score for the next level
        this.levelScore = 0;
    }

    public int getLevelScore() {
        return levelScore;
    }
    public int getScoreSum() {
        return scoreSum;
    }
}
