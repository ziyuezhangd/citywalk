package groupsix.citywalk.service;

import groupsix.citywalk.model.City;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.model.Station;
import groupsix.citywalk.service.Level;
import groupsix.citywalk.util.Save;

import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game implements Save {
    private Player player;
    private City city;
    private int levelCount;
    private int levelNum = 3;  //number of level
    private int[] levelTime = {40, 70, 100};
    private int[] levelGem = {1, 2, 3};
    private int[] levelBudget = {300, 700, 1200};

    public Game() {
    }
    public void startGame(){
        Station location = City.getStationByName("UCD");
        player = new Player("TestPlayer", location);
        city = new City();
        levelCount = 1;
    }
    public void playingGame(){
        boolean flag = false;
        for(int i = 0; i < levelNum; i++){
            Level level = new Level(levelCount, levelTime[i], levelGem[i], levelBudget[i], player, city);
            flag = level.levelPlay();
            if(flag){
                levelCount++;
            }else{
                break;
            }
        }
        if(flag){
            System.out.println("Passed!");
        }else{
            System.out.println("Failed!");
        }
    }


    @Override
    public void save() throws IOException {
        Map<String, Integer> scoreMap = new HashMap<>();

        // 读取现有分数
        Path path = Paths.get("players_scores_sum.txt");
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                scoreMap.put(parts[0], Integer.parseInt(parts[1]));
            }
        }

        // 更新当前玩家分数
        scoreMap.put(player.getPlayerName(), player.getScoreSum());

        // 排序
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scoreMap.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // 重写文件
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(path))) {
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                out.println(entry.getKey() + "," + entry.getValue());
            }
        }
    }

}
