package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.service.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class GameOverController extends Controller {
    @FXML
    private Label scoreLabel;
    @FXML
    private Label carbonFPLabel;

    @Override
    public void nextScene() {
        try {
            game.getPlayer().save();
            main.showScoreBoardScene();
        } catch (Exception e) {
            System.out.println("Error transitioning to the score board screen: " + e.getMessage());
        }

    }
    public void setUpGame(Game game) {
        this.game = game;
        Player player = game.getPlayer();
        scoreLabel.setText("Your total Score is: " + player.getScoreSum());
        carbonFPLabel.setText("Your carbon footprint is: " + player.getCarbonFP());
    }
}
