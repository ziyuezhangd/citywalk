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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
    @FXML
    private Tooltip timeTooltip;
    @FXML
    private Tooltip carbonTooltip;
    @FXML
    private Tooltip gemTooltip;
    @FXML
    private final Image gem = new Image(getClass().getResourceAsStream("/groupsix/citywalk/pics/gem.png"));
    private HashMap<String, ImageView> gemList = new HashMap<>();
    private FXMLLoader fxmlLoader;


    @FXML
    public void initGem() {
        // 绘制Gem
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
            gemList.put(stationName, gemImageView);
        }
    }

    @FXML
    public void initLevel() {
        // 设置标题
        levelLabel.setText("LEVEL" + game.getLevelCount());
        // 设置进度条相关
        timeProgress.setProgress(1.0);
        carbonProgress.setProgress(1.0);
        gemProgress.setProgress(0.0);
        timeMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelTime()));
        carbonMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelBudget()));
        gemMaxLabel.setText(String.valueOf(game.getCurrentLevel().getLevelGem()));
        timeTooltip.setText(String.valueOf(game.getCurrentLevel().getLevelTime()));
        carbonTooltip.setText(String.valueOf(game.getCurrentLevel().getLevelBudget()));
        gemTooltip.setText(String.valueOf(game.getCurrentLevel().getLevelGem()));
        // 通知Player初始当前关卡数据
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
            displayRoutes();
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
            displayRoutes();
        }
    }
    @FXML
    private void displayRoutes() {
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
                setFont(Font.font(18));
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
        // 将所选路线传递给Level - Level中会更新Player里的数据
        game.getCurrentLevel().startTrip(fromTextField.getText(), toTextField.getText(), selectedIndex);
        // 弹出教育弹窗

        // 清空ListView
        ObservableList<String> emptyList = FXCollections.observableArrayList();
        routesLV.setItems(emptyList);
        // 检查Gem是否获取
        if (game.getCurrentLevel().checkGem()) {
            ImageView gemCollected = gemList.get(City.getStationByLocation(game.getPlayer().getPlayerLocation()).getStationName());
            gemCollected.setVisible(false);
        }
        // 更新界面玩家数据显示
        // 更新进度条
        double timeP = Math.max(1.0 - ((double) game.getPlayer().getTimeSpent() / (double) game.getCurrentLevel().getLevelTime()), 0.0);
        double carbonP = Math.max(1.0 - ((double) game.getPlayer().getCarbonFP() / (double) game.getCurrentLevel().getLevelBudget()), 0.0);
        double gP = ((double) game.getPlayer().getGemCollected() / (double) game.getCurrentLevel().getLevelGem());
        timeProgress.setProgress(timeP);
        carbonProgress.setProgress(carbonP);
        gemProgress.setProgress(gP);
        timeTooltip.setText(String.valueOf(game.getCurrentLevel().getLevelTime() - game.getPlayer().getTimeSpent()));
        carbonTooltip.setText(String.valueOf(game.getCurrentLevel().getLevelBudget() - game.getPlayer().getCarbonFP()));
        gemTooltip.setText(String.valueOf(game.getPlayer().getGemCollected()));
        // 更新分数
        scoreLabel.setText(String.valueOf(game.getPlayer().getScoreLevel()));
        // 更新From&To label
        fromTextField.setText(City.getStationByLocation(game.getPlayer().getPlayerLocation()).getStationName());
        toTextField.setText(null);
        // 更新玩家位置
        displayMarker();
        // 判断玩家状态：进入下一关、死亡、全部通关
        if (game.getCurrentLevel().checkAlive()) {
            // 玩家活着
            if (game.getLevelCount() == game.getLevelTotal()) {
                gameWin();
            } else {
                nextScene();
            }
        } else {
            // 玩家死了
            gameOver();
        }
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
    public void gameOver() {
        try {
            main.showGameOverScene();
        } catch (Exception e) {
            System.out.println("Error transitioning to the gameOver screen" );
            e.printStackTrace();
        }
    }
    public void gameWin() {
        // 展示gameWin
        System.out.println("Winning!" );
    }
    @Override
    public void nextScene() {
        try {
            main.showNextUpScene();
        } catch (Exception e) {
            System.out.println("Error transitioning to the levelUp screen" );
            e.printStackTrace();
        }
    }

}