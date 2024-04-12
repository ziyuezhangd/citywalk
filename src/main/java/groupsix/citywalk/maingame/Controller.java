package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import javafx.event.ActionEvent;

public abstract class Controller {

    protected Main main;     // 用于场景切换等主要操作的引用
    protected Player player; // 保持当前玩家的引用

    // 设置主类引用，所有控制器都需要
    public void setMain(Main main) {
        this.main = main;
    }

    // 初始化玩家，所有控制器都需要这个来设置或更新玩家信息
    public void setupPlayer(Player player) {
        this.player = player;
    }

    // 抽象方法，用于定义转换到下一个场景的具体逻辑
    public abstract void nextScene();

}
