package groupsix.citywalk;

import java.util.ArrayList;

public class Level {
    private int levelCount;
    private int levelTime;
    private int levelGem;
    private int levelFP;
    private Player myPlayer;
    private City myCity;
    private ArrayList<Location> gemLocation = new ArrayList<>();

    public Level(int levelCount, int levelTime, int levelGem, int levelFP, Player myPlayer, City myCity) {
        this.levelCount = levelCount;
        this.levelTime = levelTime;
        this.levelGem = levelGem;
        this.levelFP = levelFP;
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
        if(myPlayer.getTimeSpent() > levelTime || myPlayer.getCarbonFP() > levelFP){
            check =false;
        }
        return check;
    }

    private boolean checkGem(){
        boolean check = false;
        // 查找myPlayer 的location 在不在gemLocation 中。
        // 如果在gemLocation中删除此location instance 并改变check 为 true
        // 如果不在，不做任何操作
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
            Trip myTrip = new Trip(myPlayer.getLocation(), end); // need to be finished in city class ( func : getStationByLocation)
            ArrayList<Route> routePlan = myTrip.getRoutePlan();
            // user will choose  which one they need
            Route myRoute = routePlan.get(0);
            myPlayer.routeSelect(myRoute);
            myPlayer.finishTrip(myRoute);
            if(checkAlive()){
                if(checkGem()){
                    myPlayer.gemCollect();
                }
                if((myPlayer.getTimeSpent() == levelTime || myPlayer.getCarbonFP() == levelFP) && !gemLocation.isEmpty()){
                    return false;
                }
            } else{
                return false;
            }
        }
        return  true;
    }
}
