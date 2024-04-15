package groupsix.citywalk.model;
import groupsix.citywalk.util.Save;


import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class Player implements Save {
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
    public Player(String playerName, Location defaultLocation) {
        this.playerName = playerName;
        this.carbonFP = 0;
        this.gemCollected = 0;
        this.timeSpent = 0;
        this.playerLocation = defaultLocation;
        //Initialize score
        this.levelScore = 0;
        this.scoreSum = 0;
    }

    //Test save method
//    public Player(String playerName, int scoreSum) {
//        this.playerName = playerName;
//        this.scoreSum = scoreSum;
//    }

    public String getPlayerName() {
        return playerName;
    }
    // Update time and carbonFP after each trip
    public void finishTrip(Route route) {
        timeSpent += route.getTime();
        carbonFP += route.getCarbonFP();
        playerLocation = route.getEnd();
        levelScore += calScore(route);
        scoreSum += calScore(route);
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

    public int calScore(Route route) {


//        getModeNumber()的值 1-3
//        getCarbonFP()的值 2-960g
//        getTime()的值 4-140min

        // 防止分母为零的情况
        double modeValue = route.getModeNumber() == 0 ? 1 : route.getModeNumber();
        double fpValue = route.getCarbonFP() == 0 ? 1 : route.getCarbonFP();
        double timeValue = route.getTime() == 0 ? 1 : route.getTime();

        double modeScore = modeValue / 3.0;
        double FPScore = fpValue / 960.0;
        double timeScore = timeValue / 140.0;

        // 并限制最大值为1
        double invertedModeScore = Math.min(1.0, 1.0 / modeScore);
        double invertedFPScore = Math.min(1.0, 1.0 / FPScore);
        double invertedTimeScore = Math.min(1.0, 1.0 / timeScore);

        double totalScore = invertedModeScore + invertedFPScore + invertedTimeScore;

        scoreSum = (int) Math.round(totalScore);
        return scoreSum;
    }

    public int getLevelScore() {
        return levelScore;
    }
    public int getScoreSum() {
        return scoreSum;
    }


    @Override
    public void save() {
        File file = new File("playerScores.txt");
        List<PlayerScore> scores = new ArrayList<>();

        if (file.exists()) {
            // 文件存在，读取现有数据
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        scores.add(new PlayerScore(name, score));
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }

        // 将新玩家的得分添加到列表
        scores.add(new PlayerScore(this.playerName, this.scoreSum));

        // 对列表进行排序，得分高的在前
        Collections.sort(scores, (a, b) -> Integer.compare(b.score, a.score));

        // 将更新后的得分列表写回文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (PlayerScore ps : scores) {
                writer.write(ps.name + "," + ps.score + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private static class PlayerScore {
        String name;
        int score;

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

}
