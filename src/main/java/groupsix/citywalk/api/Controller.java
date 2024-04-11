package groupsix.citywalk.api;

import groupsix.citywalk.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Controller {
    // LogIn View
    @FXML
    private TextField nameTextField;
    @FXML
    private Button startGameButton;
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleStartGame(ActionEvent event) throws IOException {
        String playerName = nameTextField.getText();
        switchToGameScene(playerName);
    }

    // Game View
    private void switchToGameScene(String playerName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/Game.fxml"));
        Parent root = loader.load();
        Controller gameController = loader.getController();
        primaryStage.setScene(new Scene(root));
    }

    private void initGame(){
        //
    }

    private void initMap(){
        Image grass = new Image("/groupsix/citywalk/pics/grass.png");
        
    }
}