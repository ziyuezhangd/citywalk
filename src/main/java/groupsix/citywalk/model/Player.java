package groupsix.citywalk.model;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int carbonFP;
    private int gemCollected;
    private ArrayList<Route> levelSelectedRoutes = new ArrayList<>();
    private ArrayList<ArrayList<Route>> gameSelectedRoutes = new ArrayList<>();
    private int timeSpent;
    private Location playerLocation;
    private int levelScore;
    private int scoreSum;

    // Constructor
    public Player(String playerName, Station location) {
        this.playerName = playerName;
        this.carbonFP = 0;
        this.gemCollected = 0;
        this.timeSpent = 0;
        this.playerLocation = location;
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
        playerLocation = route.getEnd();
    }
    public void startNewLevel(){
        this.carbonFP = 0;
        this.gemCollected = 0;
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
        playerLocation = route.getEnd();
    }

    public int gemCollect(){
        gemCollected += 1;
        return gemCollected;
    }

    public Location getPlayerLocation(){
        return playerLocation;
    }

    public int getTimeSpent(){
        return timeSpent;
    }

    public int getGemCollected(){
        return gemCollected;
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
