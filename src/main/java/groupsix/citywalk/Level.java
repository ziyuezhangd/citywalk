package groupsix.citywalk;

public class Level {
    private int levelCount;
    private int levelTime;
    private int levelGem;
    private int levelFP;
    private Player myPlayer;
    private City myCity;

    public Level(int levelCount, int levelTime, int levelGem, int levelFP, Player myPlayer, City myCity) {
        this.levelCount = levelCount;
        this.levelTime = levelTime;
        this.levelGem = levelGem;
        this.levelFP = levelFP;
        this.myPlayer = myPlayer;
        this.myCity = myCity;
    }

    public boolean levelPlay(){
        System.out.println("This is Level: "+ levelCount);
        return  true;
    }
}
