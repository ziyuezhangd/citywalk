package groupsix.citywalk;

public class Score {
    private int levelScore;
    private int scoreSum;

    // Constructor. Initialize level score and total score
    public Score() {
        this.levelScore = 0;
        this.scoreSum = 0;
    }

    // Calculate the level score based on time and FP
    public void calScore(int time, int playerFP) {
        this.levelScore = (int) (Math.log(1000 - (1 * Math.log(time)) - (3 * Math.log(playerFP))) * 10);
    }

    // Add level score to total score
    public void updateScores(int levelScore, int scoreSum) {
        this.scoreSum += levelScore;
        this.levelScore = 0;
    }

    // Getter for level score
    public int getLevelScore() {
        return levelScore;
    }

    // Getter for total score
    public int getScoreSum() {
        return scoreSum;
    }

}
