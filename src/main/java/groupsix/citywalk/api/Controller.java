package groupsix.citywalk.api;

import groupsix.citywalk.model.Player;
import groupsix.citywalk.service.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
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
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private Label levelLabel;
    @FXML
    private GridPane mapGrid;
    @FXML
    private ProgressBar timeProgress;
    @FXML
    private ProgressBar carbonProgress;
    @FXML
    private ProgressBar gemProgress;

    @FXML
    private void switchToGameScene(String playerName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/Game.fxml"));
        Parent root = loader.load();
        Controller gameController = loader.getController();
        primaryStage.setScene(new Scene(root));
        initGame(playerName);
    }
    @FXML
    private void initGame(String playerName){
        Game game = new Game();
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label chosenLabel = (Label) event.getSource();
        String stationName = chosenLabel.getId();
        stationName = stationName.substring(0, stationName.length()-"Label".length());
        if (!stationName.equals(fromTextField.getText())){
            toTextField.setText(stationName);
            generateRoutes();
        }
    }
    @FXML
    private void handleCircleClick(MouseEvent event) {
        Circle chosenCircle = (Circle) event.getSource();
        String stationName = chosenCircle.getId();
        stationName = stationName.substring(0, stationName.length()-"Circle".length());
        if (!stationName.equals(fromTextField.getText())){
            toTextField.setText(stationName);
            generateRoutes();
        }
    }
    @FXML
    private void generateRoutes() {
        String startName = fromTextField.getText();
        String endName = toTextField.getText();
    }
    @FXML
    private void handleResetButton(ActionEvent event) {
        toTextField.setText(null);
    }
    @FXML
    private void handleStartTrip(ActionEvent event){
        openEduPopupWindow();
    }

    //LevelUp View

    //GameOver View

    //GameFinish View

    //Edu Popup View
    @FXML
    private void openEduPopupWindow(){
        //
    }
}