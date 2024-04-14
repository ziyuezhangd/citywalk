package groupsix.citywalk.api;

import groupsix.citywalk.maingame.Controller;
import groupsix.citywalk.model.*;
import groupsix.citywalk.service.Game;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;


public class GameController extends Controller {
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private Label levelLabel;
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
    @FXML
    private Button startButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private ImageView markerIV;
    private FXMLLoader fxmlLoader;


    @FXML
    public void initMap() {
        // 绘制Gem
        Image gem = new Image(getClass().getResourceAsStream("/groupsix/citywalk/pics/gem.png"));
        for (Location location: game.getCurrentLevel().getGemLocation()) {
            System.out.println(location.getX());
            System.out.println(location.getY());
            ImageView gemImageView = new ImageView(gem);
            gemImageView.setFitWidth(30.0);
            gemImageView.setPreserveRatio(true);
            gemImageView.setPickOnBounds(false);
            // 获取对应Station位置
            String stationName = City.getStationByLocation(location).getStationName();
            System.out.println("Gem:" + stationName);
            Circle stationCircle = (Circle) fxmlLoader.getNamespace().get(stationName + "Circle");
            gemImageView.setLayoutX(stationCircle.getCenterX() - 15);
            gemImageView.setLayoutY(stationCircle.getCenterY() - 21);
            gemAnchorPane.getChildren().add(gemImageView);
        }
        // 绘制玩家位置
        displayMarker();
    }

    @FXML
    public void initLevel() {
        levelLabel.setText("TESTING: LEVEL" + game.getLevelCount());
        timeProgress.setProgress(1.0);
        carbonProgress.setProgress(1.0);
        gemProgress.setProgress(0.0);
        timeMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelTime()));
        carbonMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelBudget()));
        gemMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelGem()));
        game.getPlayer().startNewLevel();
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label chosenLabel = (Label) event.getSource();
        String stationName = chosenLabel.getId();
        stationName = stationName.substring(0, stationName.length()-"Label".length());
        if (!stationName.equals(fromTextField.getText())){
            toTextField.setText(stationName);
            startButton.setDisable(true);
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
            startButton.setDisable(true);
            generateRoutes();
        }
    }
    @FXML
    private void generateRoutes() {
        String startName = fromTextField.getText();
        String endName = toTextField.getText();
        Trip trip = new Trip(City.getStationByName(startName), City.getStationByName(endName));
        ArrayList<String> routeList = new ArrayList<>();
        for (Route route: trip.getRoutePlan()) {
            routeList.add(route.toString());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(routeList);
        // 设置自动换行
        routesLV.setCellFactory(param -> new ListCell<String>() {
            {
                setPrefWidth(200);
                setWrapText(true);
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
            }
        });
        // 添加路线
        routesLV.setItems(observableList);
    }
    @FXML
    private void handleRouteSelect(MouseEvent event) {
        int selectedIndex = routesLV.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            System.out.println("Selected item index: " + selectedIndex);
            startButton.setDisable(false);
        } else {
            startButton.setDisable(true);
            System.out.println("No item selected");
        }
    }
    @FXML
    private void handleResetButton(ActionEvent event) {
        // 清空To和ListView
        toTextField.setText(null);
        ObservableList<String> emptyList = FXCollections.observableArrayList();
        routesLV.setItems(emptyList);
    }
    @FXML
    private void handleStartTrip(ActionEvent event) {
        // 确认路线选择
        int selectedIndex = routesLV.getSelectionModel().getSelectedIndex();
        // 将所选路线传递给Level
        game.getCurrentLevel().startTrip(fromTextField.getText(), toTextField.getText(), selectedIndex);
        // 弹出教育弹窗

        // 更新界面玩家数据
        double timeP = Math.max(1.0 - ((double) game.getPlayer().getTimeSpent() / (double) game.getCurrentLevel().getLevelTime()), 0.0);
        double carbonP = Math.max(1.0 - ((double) game.getPlayer().getCarbonFP() / (double) game.getCurrentLevel().getLevelBudget()), 0.0);
        double gP = ((double) game.getPlayer().getGemCollected() / (double) game.getCurrentLevel().getLevelGem());
        timeProgress.setProgress(timeP);
        carbonProgress.setProgress(carbonP);
        gemProgress.setProgress(gP);
        scoreLabel.setText(String.valueOf(game.getPlayer().getScoreLevel()));
    }
    @FXML
    public void displayMarker() {
        Station playerStation = City.getStationByLocation(game.getPlayer().getPlayerLocation());
        System.out.println(playerStation.getStationName());
        Circle playerCircle = (Circle) fxmlLoader.getNamespace().get(playerStation.getStationName() + "Circle");
        markerIV.setLayoutX(playerCircle.getCenterX() - 13);
        markerIV.setLayoutY(playerCircle.getCenterY() - 23);
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