package groupsix.citywalk.api;

import groupsix.citywalk.maingame.Controller;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.service.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller {
    @FXML
    TextField nameTextField;

    @FXML
    public void handleStartGame(ActionEvent event){
        String playerName = nameTextField.getText().trim();
        if (!playerName.isEmpty()) {
            game = new Game(playerName);
            main.setGame(game);
            nextScene();
        }else {
            nameTextField.setPromptText("Invalid input!");  // 提示用户输入有效名称
        }
    }

    @FXML
    public void nextScene() {
        try {
            main.showGameScene();
        } catch (IOException e) {
            System.out.println("Error transitioning to the game screen: " + e.getMessage());
        }

    }
}
