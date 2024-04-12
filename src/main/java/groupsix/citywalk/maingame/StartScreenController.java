package groupsix.citywalk.maingame;
import groupsix.citywalk.model.Player;
import groupsix.citywalk.model.Station;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StartScreenController extends Controller {

    @FXML
    private TextField nameField;

    Station defLocation = new Station(8, 0, "Dundrum"); //Default start location of user

//    这样就可以用Main里面场景切换的func了
    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void nextScene() {
        try {
            main.showGameScreen();
        } catch (Exception e) {
            System.out.println("Error transitioning to the game screen: " + e.getMessage());
        }
    }

    // Function to pass
    public Player getPlayer(){
        return player;
    }


    @FXML
    private void handleStartGame() throws Exception {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            player = new Player(name, defLocation);
            main.setPlayer(player);
            nextScene();
        }else {
            nameField.setPromptText("Invalid input!");  // 提示用户输入有效名称
        }
    }


}
