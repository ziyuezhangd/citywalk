package groupsix.citywalk.service;

import groupsix.citywalk.model.*;
import groupsix.citywalk.util.MapConfig;
import groupsix.citywalk.util.Save;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Level implements Save {
    private int levelCount;
    private int levelTime;
    private int levelGem;
    private int levelBudget;
    private Player player;
    private ArrayList<Location> gemLocation = new ArrayList<>();
    private ArrayList<Trip> levelTrips = new ArrayList<>();

    public Level(int levelCount, int levelTime, int levelGem, int levelBudget, Player player) {
        this.levelCount = levelCount;
        this.levelTime = levelTime;
        this.levelGem = levelGem;
        this.levelBudget = levelBudget;
        this.player = player;
        createGem();
    }
    public ArrayList<Location> getGemLocation() {
        return gemLocation;
    }
    public int getLevelTime() {
        return levelTime;
    }
    public int getLevelBudget() {
        return levelBudget;
    }
    public int getLevelGem() {
        return levelGem;
    }
    private void createGem(){
        Random random = new Random();
        int n = City.getStationList().size();
        while (gemLocation.size() < levelGem) {
            int randomNumber = random.nextInt(n);
            Location testGem = City.getStationList().get(randomNumber);
            if (!gemLocation.contains(testGem) && !testGem.equals(player.getPlayerLocation())) {
                gemLocation.add(testGem);
            }
        }
    }

    public void startTrip(String startName, String endName, int routeNo) {
        // Trip中记录所选路线
        Station start = City.getStationByName(startName);
        Station end = City.getStationByName(endName);
        Trip trip = new Trip(start, end);
        trip.selectRoute(routeNo);
        // Level中记录旅程
        levelTrips.add(trip);
        // Player中更新玩家数据
        player.finishTrip(trip);
        // 检查Gem

    }
    public boolean checkAlive(){
        boolean check = true;
        if (player.getTimeSpent() > levelTime || player.getCarbonFP() > levelBudget){
            check =false;
        }
        return check;
    }

    public boolean checkGem(){
        boolean check = false;
        Location currentPlayerLocation = player.getPlayerLocation();
        Iterator<Location> iterator = gemLocation.iterator();
        while (iterator.hasNext()) {
            Location gem = iterator.next();
            if (gem.equals(currentPlayerLocation)) {
                iterator.remove();
                check = true;
                player.gemCollect();
                break;
            }
        }
        return check;
    }

    public boolean checkContinue() {
        return checkAlive() && player.getGemCollected() < levelGem;
    }


    @Override
    public void save() throws IOException {
        String fileName = "level_" + levelCount + "_" + player.getPlayerName() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Player: " + player.getPlayerName());
            out.println("LevelCount: " + levelCount);
            out.println("LevelTime: " + levelTime);
            out.println("LevelFP: " + levelBudget);
//            out.println("Route: " + myRoute);
//            out.println("Transport Mode : " + myRoute.getModeNumber());
            out.println("Score: " + player.getScoreLevel());
        }
    }
}
