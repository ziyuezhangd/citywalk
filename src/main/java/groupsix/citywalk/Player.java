package groupsix.citywalk;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String playerName;

    // A player has a Score instance
    private Score playerScore;

    // The player’s carbon footprint consumption in the current level
    private int carbonFP;

    // The number of gems obtained by the player in the current level
    private int gemCollect;

    // The route chosen by the player in the current level
    private ArrayList<Route> levelSelectedRoutes = new ArrayList<>();
    private ArrayList<ArrayList<Route>> gameSelectedRoutes = new ArrayList<>();

    // Time the user has spent in the current level
    private int timeSpent;

    private Station location;

    // Constructor
    public Player(String playerName, Station location) {
        this.playerName = playerName;
        this.playerScore = new Score();
        this.carbonFP = 0;
        this.gemCollect = 0;
        this.timeSpent = 0;
        this.location = location;
    }

    // Set player name
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    //Initialise player status after a level
    public void initStatus(){
        this.carbonFP = 0;
        this.gemCollect = 0;
        this.timeSpent = 0;
        gameSelectedRoutes.add(levelSelectedRoutes);
        levelSelectedRoutes = new ArrayList<>();
    }
    // Update time and carbonFP after each trip
    public void updateStatus(Route route) {
        timeSpent += route.getTime();
        carbonFP += route.getCarbonFP();
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
}
