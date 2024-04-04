package groupsix.citywalk.service;

import groupsix.citywalk.model.*;
import groupsix.citywalk.util.MapConfig;
import groupsix.citywalk.util.Save;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Level implements Save {
    private int levelCount;
    private int levelTime;
    private int levelGem;
    private int levelBudget;
    private Player myPlayer;
    private City myCity;
    private ArrayList<Location> gemLocation = new ArrayList<>();
    private Route myRoute;

    public Level(int levelCount, int levelTime, int levelGem, int levelBudget, Player myPlayer, City myCity) {
        this.levelCount = levelCount;
        this.levelTime = levelTime;
        this.levelGem = levelGem;
        this.levelBudget = levelBudget;
        this.myPlayer = myPlayer;
        this.myCity = myCity;
    }
    public void createGem(){
        for (int i=0; i<levelGem; i++){
            Location location = new Location(MapConfig.stationLocations[i][0], MapConfig.stationLocations[i][1]);
            gemLocation.add(location);
        }
    }
    private boolean checkAlive(){
        boolean check = true;
        if (myPlayer.getTimeSpent() > levelTime || myPlayer.getCarbonFP() > levelBudget){
            check =false;
        }
        return check;
    }

    private boolean checkGem(){
        boolean check = false;
        Location currentPlayerLocation = myPlayer.getPlayerLocation();
        Iterator<Location> iterator = gemLocation.iterator();
        while (iterator.hasNext()) {
            Location gem = iterator.next();
            if (gem.isSameLocation(currentPlayerLocation)) {
                iterator.remove();
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean levelPlay(){
        System.out.println("This is Level: "+ levelCount);
        if(levelCount != 1){
            myPlayer.startNewLevel();
        }
        createGem();
        while (!gemLocation.isEmpty()){

            Location end = gemLocation.get(1);
            //user input end location
            Trip myTrip = new Trip(City.getStationByLocation(myPlayer.getPlayerLocation()), City.getStationByLocation(end));
            ArrayList<Route> routePlan = myTrip.getRoutePlan();
            // user will choose  which one they need
            myRoute = routePlan.get(0);
            myPlayer.routeSelect(myRoute);
            myPlayer.finishTrip(myRoute);
            if (checkAlive()){
                if (checkGem()){
                    myPlayer.gemCollect();
                }
                if ((myPlayer.getTimeSpent() == levelTime || myPlayer.getCarbonFP() == levelBudget)
                        && !gemLocation.isEmpty()){
                    return false;
                }
            } else{
                return false;
            }
        }
        return true;
    }

    @Override
    public void save() throws IOException {
        String fileName = "level_" + levelCount + "_" + myPlayer.getPlayerName() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Player: " + myPlayer.getPlayerName());
            out.println("LevelCount: " + levelCount);
            out.println("LevelTime: " + levelTime);
            out.println("LevelFP: " + levelBudget);
            out.println("Route: " + myRoute);
            out.println("Transport Mode : " + myRoute.getModeNumber());
            out.println("Score: " + myPlayer.getLevelScore());
        }
    }
}
