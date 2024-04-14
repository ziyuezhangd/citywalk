package groupsix.citywalk.service;

import groupsix.citywalk.model.City;
import groupsix.citywalk.model.Location;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.model.Station;
import groupsix.citywalk.service.Level;
import groupsix.citywalk.util.Save;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Game implements Save {
    private Player player;
    private City city;
    private int levelCount;
    private int levelTotal= 3;  // total number of level
    private int[] levelTime = {50, 70, 100};
    private int[] levelGem = {1, 2, 3};
    private int[] levelBudget = {200, 500, 800};
    private ArrayList<Level> levelLog = new ArrayList<>();

    public Game(String playerName) {
        this.city = new City();
        Location defaultLocation = (Location) City.getStationByName("UCD");
        this.player = new Player(playerName, defaultLocation);
        levelCount = 1;
        levelLog.add(new Level(levelCount, levelTime[0], levelGem[0], levelBudget[0], player, city));
    }

//    public void playingGame() {
//        boolean flag = false;
//        for (int i = 0; i < levelNum; i++) {
//            Level level = new Level(levelCount, levelTime[i], levelGem[i], levelBudget[i], player, city);
//            flag = level.levelPlay();
//            if (flag) {
//                levelCount++;
//            } else {
//                break;
//            }
//        }
//        if (flag) {
//            System.out.println("Passed!");
//        } else {
//            System.out.println("Failed!");
//        }
//    }
    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return levelLog.get(levelCount - 1);
    }

    public int getLevelCount() {
        return levelCount;
    }
    public int getLevelTotal() {
        return levelTotal;
    }

    // An inner class used to save the ranking list
    public static class PlayerScore {
        private String playerName;
        private int scoreSum;

        public PlayerScore(String playerName, int scoreSum) {
            this.playerName = playerName;
            this.scoreSum = scoreSum;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScoreSum() {
            return scoreSum;
        }

    }

    @Override
    public void save() throws IOException {
        List<PlayerScore> scores = new ArrayList<>();
        File file = new File("players_and_scores.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1].trim());
                    scores.add(new PlayerScore(name, score));
                }
            }
        }

        // Add the current player
        scores.add(new PlayerScore(player.getPlayerName(), player.getScoreSum()));

        // Sort by score in descending order
        // If the scores are the same then sort by player name in ascending order
        scores.sort((p1, p2) -> {
            if (p1.getScoreSum() == p2.getScoreSum()) {
                return p1.getPlayerName().compareTo(p2.getPlayerName());
            }
            return Integer.compare(p2.getScoreSum(), p1.getScoreSum());
        });

        // Write sorted data back to file
        try (PrintWriter out = new PrintWriter(new FileWriter(file, false))) {
            for (PlayerScore ps : scores) {
                out.println(ps.getPlayerName() + "," + ps.getScoreSum());
            }
        }
    }
}
