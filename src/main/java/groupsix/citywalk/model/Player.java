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
//        假设地图是20*20的网 一个格代表500m
//        getModeNumber()的值 1-3
//        getCarbonFP()的值 0-400g
//        getTime()的值 看具体 timeFactor 的赋值 假设在1-50内


        // Weightung values of different factors
        // 目前的权重，最终levelScore范围在1000以内
        double modeNumberWeight = 200.0;
        double carbonFPWeight = 500.0;
        double timeWeight = 300.0;

        levelScore = 0;
        for (Route route : levelSelectedRoutes) {
            double carbonFP = (double) Math.max(1, route.getCarbonFP());
            levelScore += (int) (modeNumberWeight * (1.0 / route.getModeNumber()) + carbonFPWeight * (1.0 / route.getCarbonFP()) + timeWeight * (1.0 / route.getTime()));
        }

        // 可以选择在这里更新总分,或者放到Level中
        // this.scoreSum += this.levelScore;
    }

    public int getLevelScore() {
        return levelScore;
    }
    public int getScoreSum() {
        return scoreSum;
    }
}
