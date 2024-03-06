package groupsix.citywalk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreManager {
    // A HashMap to store all the players' names and their total scores
    private HashMap<String, Integer> playerScores;

    // Constructor. Initialize HashMap
    public ScoreManager() {
        this.playerScores = new HashMap<>();
    }

    // Add a new player information ( name and total score) to hashmap
    public void updatePlayerScore(String playerName, int score) {
        this.playerScores.put(playerName, score);
    }

    // Get the ranking list sorted by total score
    public String[][] getRankingList() {
        List<Map.Entry<String, Integer>> sortedEntries = playerScores.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        String[][] rankingList = new String[sortedEntries.size()][2];
        for (int i = 0; i < sortedEntries.size(); i++) {
            Map.Entry<String, Integer> entry = sortedEntries.get(i);
            rankingList[i][0] = entry.getKey(); // 玩家名称
            rankingList[i][1] = String.valueOf(entry.getValue()); // 玩家分数，转换为字符串
        }

        return rankingList;
    }

    // Test rankingList method
//        public static void main(String[] args) {
//            ScoreManager scoreManager = new ScoreManager();
//            scoreManager.updatePlayerScore("Alice", 950);
//            scoreManager.updatePlayerScore("Bob", 1200);
//            scoreManager.updatePlayerScore("Charlie", 700);
//            scoreManager.updatePlayerScore("Diana", 1150);
//
//            String[][] rankingList = scoreManager.getRankingList();
//
//            for (String[] playerScore : rankingList) {
//                System.out.println("Player: " + playerScore[0] + ", Score: " + playerScore[1]);
//            }
//        }
}
