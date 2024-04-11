package groupsix.citywalk.api;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField playerNameField;

    @FXML
    private Button startGameButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    // 需绑定登录界面上的Start按钮
    private void handleStartGame(ActionEvent event) {
        String playerName = playerNameField.getText();
        if (playerName.isEmpty()) {
            // 如果用户名为空，可以显示一个警告信息或者提醒用户输入用户名
            System.out.println("Please enter your username.");
            return;
        }

        // 这里可以进行一些初始化游戏的操作，例如加载游戏数据等
        switchToGameScene(playerName);
    }

    private void switchToGameScene(String playerName) {
        // 切换到游戏界面的逻辑

    }

}