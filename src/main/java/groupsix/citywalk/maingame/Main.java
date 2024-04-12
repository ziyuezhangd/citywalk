package groupsix.citywalk.maingame;

import groupsix.citywalk.model.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage primaryStage;
    private Player player;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showStartScreen();
    }

//场景切换用的
    private void showStartScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/StartScreen.fxml"));
        Parent root = loader.load();
        StartScreenController controller = loader.getController();
        controller.setMain(this);
        this.player = controller.getPlayer();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("StartGame");
        primaryStage.show();
    }
    public void showGameScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/GameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController controller = loader.getController();
        controller.setupPlayer(player);
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("PlayingGame");
    }
//    public void showNextLevelScreen() throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/NextLevelScreen.fxml"));
//        Parent root = loader.load();
//        NextLevelController controller = loader.getController();
//        controller.setupPlayer(player);
//        controller.setMain(this);
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setTitle("Next Level");
//    }


    //    public void showGameOverScreen() throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/GameOverScreen.fxml"));
//        Parent root = loader.load();
//        GameOverController controller = loader.getController();
//        controller.setupPlayer(player);
//        controller.setMain(this);
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setTitle("Game Over");
//    }

    //    public void showScoreBoardScreen() throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/ScoreBoardScreen.fxml"));
//        Parent root = loader.load();
//        ScoreBoardController controller = loader.getController();
//        controller.setupPlayer(player);
//        controller.setMain(this);
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setTitle("Game End");
//    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
