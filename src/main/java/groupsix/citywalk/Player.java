package groupsix.citywalk;
import java.util.List;

public class Player {
    private String playerName;

    // A player has a Score instance
    private Score playerScore;

    // The player’s carbon footprint consumption in the current level
    private int playerFP;

    // The number of gems obtained by the player in the current level
    private int gemCount;

    // The route chosen by the player in the current level
    private Route[] selectedRoutes;

    // Time the user has spent in the current level
    private int time;

    // Store route details
    private List<RouteDetail> routeDetails;
    private class RouteDetail {
        int carbonFP;
        int transportMode;
        int time;

        RouteDetail(int carbonFP, int modes, int time) {
            this.carbonFP = carbonFP;
            this.transportMode = modes;
            this.time = time;
        }
    }

    // Constructor
    public Player(String playerName) {
        this.playerName = playerName;
        this.playerScore = new Score();
        this.playerFP = 0;
        this.gemCount = 0;
        this.selectedRoutes = new Route[levelCount];
        for (int i = 0; i < levelCount; i++) {
            this.selectedRoutes[i] = new Route();
        }
        this.time = 0;
    }

    // Set player name
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Update time and footprint
    public void updateStatus() {
        // It's related to Level Class
        // Update time and footprint of user
        // time = selectedRoutes[level].time;
        // playerFP = seletedRoutes[level].carbonFP;
    }

    // Get player's carbon footprint
    public int getPlayerFP(int playerFP) {
        return playerFP;
    }

    // Get the number of gems the player has obtained for the current level
    public int getPlayerGem() {
        return gemCount;
    }

    // Store the routes (trips) taken and for each route
    // its carbon footprint, the number of modes used, and total travel time
    public void storeResult() {
        for (Route route : selectedRoutes) {
            if (route != null) {
                routeDetails.add(new RouteDetail(route.getCarbonFP(), route.getModes(), route.getTime()));
            }
        }
    }
}
