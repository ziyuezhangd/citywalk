package groupsix.citywalk.api;

import groupsix.citywalk.maingame.Controller;
import groupsix.citywalk.model.City;
import groupsix.citywalk.model.Location;
import groupsix.citywalk.model.Route;
import groupsix.citywalk.model.Trip;
import groupsix.citywalk.service.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;


public class GameController extends Controller {
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
    private Label timeMaxLabel;
    @FXML
    private ProgressBar carbonProgress;
    @FXML
    private Label carbonMaxLabel;
    @FXML
    private ProgressBar gemProgress;
    @FXML
    private Label gemMaxLabel;
    @FXML
    private AnchorPane gemAnchorPane;
    @FXML
    private ListView<String> routesLV;

    private FXMLLoader fxmlLoader;


    @FXML
    public void initMap() {
        Image gem = new Image("file:pics/gem.png");
        for (Location location: game.getCurrentLevel().getGemLocation()) {
            System.out.println(location.getX());
            System.out.println(location.getY());
            ImageView gemImageView = new ImageView(gem);
            gemImageView.setFitWidth(40.0);
            gemImageView.setPreserveRatio(true);
            gemImageView.setPickOnBounds(false);
            // 获取位置
            String stationName = City.getStationByLocation(location).getStationName();
            System.out.println(stationName);
            Circle stationCircle = (Circle) fxmlLoader.getNamespace().get(stationName + "Circle");
            System.out.println(stationCircle.getCenterX() - 20);
            System.out.println(stationCircle.getCenterY() - 23);
            gemImageView.setLayoutX(stationCircle.getCenterX() - 20);
            gemImageView.setLayoutY(stationCircle.getCenterY() - 23);
            gemAnchorPane.getChildren().add(gemImageView);
        }
    }

    @FXML
    public void initLevel() {
        levelLabel.setText("TESTING: LEVEL" + game.getLevelCount());
        timeProgress.setProgress(100.0);
        carbonProgress.setProgress(100.0);
        gemProgress.setProgress(0.0);
        timeMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelTime()));
        carbonMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelBudget()));
        gemMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelGem()));
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
        Trip trip = new Trip(City.getStationByName(startName), City.getStationByName(endName));
        for (Route route: trip.getRoutePlan()) {

        }
    }
    @FXML
    private void handleResetButton(ActionEvent event) {
        toTextField.setText(null);
    }
    @FXML
    private void handleStartTrip(ActionEvent event) {
        // 更新游戏进度
        // 弹出教育弹窗
        nextScene();
    }

    public void setUpFXMLLoader(FXMLLoader loader) {
        this.fxmlLoader = loader;
    }
    @Override
    public void nextScene() {
        // 触发Education弹窗
        System.out.println("Education Popup");
    }

}