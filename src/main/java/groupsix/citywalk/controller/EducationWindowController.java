package groupsix.citywalk.controller;
import groupsix.citywalk.controller.Controller;
import groupsix.citywalk.maingame.Main;
import groupsix.citywalk.model.TransportMode;
import groupsix.citywalk.model.Trip;
import groupsix.citywalk.service.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;


public class EducationWindowController extends Controller {
    @FXML
    private Label educationLabel;
    @FXML
    private Button CloseButton;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setUpGame(Game game) {
        this.game = game;
        updateEducationLabel();  // 当设置游戏数据时更新标签
    }



    // 暂存当前的Stage，当窗口被显示时会被设置
    private Stage currentStage;

    // 设置当前Stage的方法
    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }
    private void closeStage() {
        if (currentStage != null) {
            currentStage.close();
        }
    }

    private void updateEducationLabel() {
        List<Trip> levelTrips = game.getCurrentLevel().getLevelTrips();
        if (!levelTrips.isEmpty()) {
            // 获取最新的Trip
            Trip latestTrip = levelTrips.get(levelTrips.size() - 1);
            // 获取这个Trip中的所有TransportMode
            List<TransportMode> transportModes = latestTrip.getTransportTaken();
            // 使用StringBuilder来构建关于每种交通方式的描述
            StringBuilder sb = new StringBuilder();
            for (TransportMode tm : transportModes) {
                sb.append("You chose ").append(tm.getName())
                        .append(", which is ")
                        .append(tm.isEcoFriendly() ? "eco-friendly –\ngreat choice for the planet!" : "not so eco-friendly –\nconsider greener options next time.")
                        .append(".\n");
            }
            // 将构建的字符串设置到UI组件上
            educationLabel.setText(sb.toString());
        }
    }


    // 当nextScene方法被调用时，可以关闭模态窗口
    @Override
    public void nextScene() {
        try {
            closeStage(); // 关闭当前模态窗口
        } catch (Exception e) {
            System.out.println("Error transitioning to the score board screen: " + e.getMessage());
        }

    }

}
