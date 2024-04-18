package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.model.Trip;
import groupsix.citywalk.service.Game;
import groupsix.citywalk.service.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;


public class EducationWindowController extends Controller {
    @FXML
    private Label educationLabel;
    @FXML
    private Button CloseButton;

    private Game game;

    public void setGame(Game game) {
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
        Level currentLevel = game.getCurrentLevel();
        List<Trip> levelTrips = currentLevel.getLevelTrips();
        if (!levelTrips.isEmpty()) {
            Trip latestTrip = levelTrips.get(levelTrips.size() - 1);
            List<TransportMode> transportModes = latestTrip.getTransportTaken();
            StringBuilder sb = new StringBuilder();
            for (TransportMode tm : transportModes) {
                sb.append("You chose ").append(tm.getName()).append(" which is ");
                if (tm.isEcoFriendly()) {
                    sb.append("good for the environment.");
                } else {
                    sb.append("not good for the environment.");
                }
                sb.append("\n");
            }
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
