package groupsix.citywalk.maingame;

import groupsix.citywalk.controller.*;
import groupsix.citywalk.service.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private Stage primaryStage;
    private Game game;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/groupsix/citywalk/pics/gem.png")));
        primaryStage.setResizable(false);
        showLoginScene();
    }

    //场景切换用的
    private void showLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/LoginScene.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
        primaryStage.show();
    }
    public void showGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/GameScene.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setUpGame(game);
        controller.setUpFXMLLoader(loader);
        controller.setMain(this);
        controller.initLevel();
        controller.initGem();
        controller.displayMarker();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
    }
    public void showNextUpScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/LevelUpScene.fxml"));
        Parent root = loader.load();
        LevelUpController controller = loader.getController();
        controller.setUpGame(game);
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
    }


    public void showGameOverScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/GameOverScene.fxml"));
        Parent root = loader.load();
        GameFinishController controller = loader.getController();
        controller.setUpGame(game);
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
    }

    public void showGameWinScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/GameWinScene.fxml"));
        Parent root = loader.load();
        GameFinishController controller = loader.getController();
        controller.setUpGame(game);
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
    }

    public void showScoreBoardScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/ScoreBoardScene.fxml"));
        Parent root = loader.load();
        ScoreBoardController controller = loader.getController();
        controller.setUpGame(game);
        controller.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("CITYWALK");
        primaryStage.show();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void closeApplication() {
        primaryStage.close();
    }

}
