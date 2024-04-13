package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.service.Game;
import javafx.event.ActionEvent;

public abstract class Controller {

    protected Main main;     // 用于场景切换等主要操作的引用
    protected Game game;     // 保持当前游戏的引用

    // 设置主类引用，所有控制器都需要
    public void setMain(Main main) {
        this.main = main;
    }

    // 初始化游戏，所有控制器都需要这个来设置或更新游戏信息
    public void setUpGame(Game game) {this.game = game;}

    // 抽象方法，用于定义转换到下一个场景的具体逻辑
    public abstract void nextScene();

}
