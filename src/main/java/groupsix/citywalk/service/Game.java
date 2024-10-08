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


public class Game {
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
        levelLog.add(new Level(levelCount, levelTime[0], levelGem[0], levelBudget[0], player));
    }

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
    public void levelUp() {
        if (levelCount != levelTotal) {
            try {
                getCurrentLevel().save();
            } catch (IOException e) {
                System.out.println("Failed to save level results");
                e.printStackTrace();
            }
            levelCount++;
            int levelIndex = levelCount - 1;
            levelLog.add(new Level(levelCount, levelTime[levelIndex], levelGem[levelIndex], levelBudget[levelIndex],
                    player));
        }
    }

}
