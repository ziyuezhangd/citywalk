package groupsix.citywalk.model;

public class Game {
    private Player player;
    private City city;
    private int levelCount;
    private int levelNum = 3;//number of level
    private int[] levelTime = {30, 20, 10};
    private int[] levelGem = {1, 2, 3};
    private int[] levelFP = {30, 20, 10};

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
            Level level = new Level(levelCount, levelTime[i], levelGem[i], levelFP[i], player, city);
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
}
