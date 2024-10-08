package groupsix.citywalk.controller;

import groupsix.citywalk.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Button resetButton;
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
    private AnchorPane controlAnchorPane;
    @FXML
    private final Image gem = new Image(getClass().getResourceAsStream("/groupsix/citywalk/pics/purplegem.png"));
    private HashMap<String, ImageView> gemList = new HashMap<>();
    private FXMLLoader fxmlLoader;

//    private Main mainApp;

//    public void setMain(Main main) {
//        this.mainApp = main; // 保存Main实例的引用
//    }

    @FXML
    public void initGem() {
        // 绘制Gem
        for (Location location: game.getCurrentLevel().getGemLocation()) {
            System.out.println(location.getX());
            System.out.println(location.getY());
            ImageView gemImageView = new ImageView(gem);
            gemImageView.setFitWidth(35.0);
            gemImageView.setPreserveRatio(true);
            gemImageView.setPickOnBounds(false);
            // 获取对应Station位置
            String stationName = City.getStationByLocation(location).getStationName();
            System.out.println("Gem:" + stationName);
            Circle stationCircle = (Circle) fxmlLoader.getNamespace().get(stationName + "Circle");
            gemImageView.setLayoutX(stationCircle.getCenterX() - 17);
            gemImageView.setLayoutY(stationCircle.getCenterY() - 22);
            gemAnchorPane.getChildren().add(gemImageView);
            gemList.put(stationName, gemImageView);
        }
    }

    @FXML
    public void initLevel() {
        // 设置标题
        levelLabel.setText("LEVEL " + game.getLevelCount());
        // 设置From
        fromTextField.setText(City.getStationByLocation(game.getPlayer().getPlayerLocation()).getStationName());
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
        // 设置分数
        scoreLabel.setText(String.valueOf(game.getPlayer().getScoreSum()));
        // 通知Player初始当前关卡数据
        game.getPlayer().startNewLevel();
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        // 当玩家不处于Continue状态时-死亡/下一关，标签无法点击
        if (game.getCurrentLevel().checkContinue()) {
            Label chosenLabel = (Label) event.getSource();
            String stationName = chosenLabel.getId();
            stationName = stationName.substring(0, stationName.length()-"Label".length());
            if (!stationName.equals(fromTextField.getText())){
                toTextField.setText(stationName);
                startButton.setDisable(true);
                displayRoutes();
            }
        }
    }
    @FXML
    private void handleCircleClick(MouseEvent event) {
        // 当玩家不处于Continue状态时-死亡/下一关，圆点无法点击
        if (game.getCurrentLevel().checkContinue()) {
            Circle chosenCircle = (Circle) event.getSource();
            String stationName = chosenCircle.getId();
            stationName = stationName.substring(0, stationName.length()-"Circle".length());
            if (!stationName.equals(fromTextField.getText())){
                toTextField.setText(stationName);
                startButton.setDisable(true);
                displayRoutes();
            }
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
                setFont(Font.font(16));
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
        popUpEduWindow();
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
        scoreLabel.setText(String.valueOf(game.getPlayer().getScoreSum()));
        // 更新From&To label
        fromTextField.setText(City.getStationByLocation(game.getPlayer().getPlayerLocation()).getStationName());
        toTextField.setText(null);
        // 更新玩家位置
        displayMarker();
        // 判断玩家状态：是否继续本关
        if (!game.getCurrentLevel().checkContinue()) {
            // 切换下个页面
            nextScene();
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

    private void popUpEduWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/EducationWindowScene.fxml"));
            Parent root = loader.load();

            EducationWindowController controller = loader.getController();
            Stage stage = new Stage();
            controller.setCurrentStage(stage);
            controller.setUpGame(game);
            controller.setMain(main);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Education Window");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // 适当的错误处理，例如弹窗通知用户错误信息
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot load the Education window: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void nextScene() {
        // 判断：玩家是否活着
        if (game.getCurrentLevel().checkAlive()) {
            // 玩家活着，判断：是否为最后一关
            if (game.getLevelCount() == game.getLevelTotal()) {
                // 保存最后一关结果
                try {
                    game.getCurrentLevel().save();
                } catch (IOException e) {
                    System.out.println("Failed to save level result");
                    e.printStackTrace();
                }
                // 切换到GameWin
                try {
                    main.showGameWinScene();
                } catch (Exception e) {
                    System.out.println("Error transitioning to the gameWin screen");
                    e.printStackTrace();
                }
            } else {
                // 切换到LevelUp - 需交互
                // disable Game界面其他交互 - 此处仅控制按钮，标签在对应OnAction中通过if控制
                startButton.setDisable(true);
                resetButton.setDisable(true);
                // 弹出NextLevel按钮，点击时切换到LevelUp
                Button nextLevelButton = new Button("Next Level");
                nextLevelButton.setLayoutX(45);
                nextLevelButton.setLayoutY(680);
                nextLevelButton.setCursor(Cursor.HAND);
                nextLevelButton.setOpacity(0.9);
                nextLevelButton.setStyle("-fx-background-color: #0080ff; -fx-font-size: 20px; -fx-background-color: linear-gradient(#7CAFC2,deepskyblue);");
                nextLevelButton.setPrefWidth(301);
                nextLevelButton.setPrefHeight(20);
                EventHandler<ActionEvent> nextLevelHandler = event -> {
                    try {
                        main.showNextUpScene();
                    } catch (Exception e) {
                        System.out.println("Error transitioning to the levelUp screen");
                        e.printStackTrace();
                    }
                };
                nextLevelButton.setOnAction(nextLevelHandler);
                controlAnchorPane.getChildren().add(nextLevelButton);
            }
        } else {
            // 玩家死了，切换到GameOver
            try {
                main.showGameOverScene();
            } catch (Exception e) {
                System.out.println("Error transitioning to the gameOver screen" );
                e.printStackTrace();
            }
        }
    }

}