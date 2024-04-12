package groupsix.citywalk.maingame;

import groupsix.citywalk.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameScreenController extends Controller {

//    @FXML
//    private TextField numberField;  需要输入的时候打开


    @FXML
    private Label usernameLabel;

    @Override
    public void nextScene() {
        try {

//            main.showNextLevelScreen();
        } catch (Exception e) {
            // 异常处理逻辑，例如记录日志或者提供用户反馈
            System.out.println("Error transitioning to the game screen: " + e.getMessage());
        }
    }

    public void setupPlayer(Player player) {
        this.player = player;
        usernameLabel.setText(player.getPlayerName());
    }

//点击了对应的按钮之后的处理逻辑
    @FXML
    private void handleGame() {
//
    }

}
