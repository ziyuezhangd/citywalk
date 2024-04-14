package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class GameOverController extends Controller {
    @FXML
    private Label scoreLabel;

    @Override
    public void nextScene() {
        try {
//            main.showScoreBoardScene();
        } catch (Exception e) {
            System.out.println("Error transitioning to the score board screen: " + e.getMessage());
        }

    }
}
