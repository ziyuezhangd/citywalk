package groupsix.citywalk.model;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int carbonFPLevel;
    private int gemCollectedLevel;
    private int timeSpentLevel;
    private Location playerLocation;
    private int scoreLevel;
    private int scoreSum;

    // Constructor
    public Player(String playerName, Location defaultLocation) {
        this.playerName = playerName;
        this.playerLocation = defaultLocation;
        //Initialize total score
        this.scoreSum = 0;
    }

    public String getPlayerName() {
        return playerName;
    }
    // Update time and carbonFP after each trip
    public void finishTrip(Trip trip) {
        Route route = trip.getSelectedRoute();
        timeSpentLevel += route.getTime();
        carbonFPLevel += route.getCarbonFP();
        playerLocation = route.getEnd();
        scoreLevel += calScore(route);
        scoreSum += calScore(route);
    }
    public void startNewLevel(){
        // 初始化玩家当前关卡数据
        this.carbonFPLevel = 0;
        this.gemCollectedLevel = 0;
        this.timeSpentLevel = 0;
        this.scoreLevel = 0;
    }
    public int getCarbonFP() {
        return carbonFPLevel;
    }

    public int gemCollect(){
        gemCollectedLevel += 1;
        return gemCollectedLevel;
    }

    public Location getPlayerLocation(){
        return playerLocation;
    }

    public int getTimeSpent(){
        return timeSpentLevel;
    }

    public int getGemCollected(){
        return gemCollectedLevel;
    }

    public int calScore(Route route) {
//        假设地图是10*10的网 一个格代表500m
//        getModeNumber()的值 1-3
//        getCarbonFP()的值 2-960g
//        getTime()的值 4-140min

        // Weighting values of different factors
        // 目前的权重，最终levelScore范围在1000以内
        double modeNumberWeight = 200.0;
        double carbonFPWeight = 500.0;
        double timeWeight = 300.0;

        int score = (int) (modeNumberWeight * (1.0 / route.getModeNumber()) + carbonFPWeight * (1.0 / route.getCarbonFP()) + timeWeight * (1.0 / route.getTime()));
        return score;
    }

    public int getScoreLevel() {
        return scoreLevel;
    }
    public int getScoreSum() {
        return scoreSum;
    }
}
