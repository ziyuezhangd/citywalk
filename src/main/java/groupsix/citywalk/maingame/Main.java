package groupsix.citywalk.maingame;
import groupsix.citywalk.view.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//public class Main extends Application {
//    //test below
//    Stage window;
//    Scene scene1, scene2_1, scene2_2, scene2_3,scene3, scene4;
//    Button button;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    @Override
//    public void start(Stage initialStage) throws Exception {
//        this.window = initialStage;
//        //Scene 1
//        Label label1 = new Label("Welcome to CityWalk!");
//        Button buttonStartGame = new Button();
//        buttonStartGame.setText("Enter Game");
//
//        //uses lambda expressions to handle button events
//        buttonStartGame.setOnAction(e -> window.setScene(scene2_3));
//
//        //Layout
//        StackPane layout1 = new StackPane();
//        layout1.getChildren().addAll(label1,buttonStartGame);
//        scene1 = new Scene(layout1, 700, 500);
//
//        //Scene 2.3
//        Button buttonSelectRoute = new Button("Start Trip");
//        buttonSelectRoute.setOnAction(e -> {
//            EducationBox.display("Education message from Education to be added here");
//            window.setScene(scene3);
//        });
//
//        StackPane layout2_3 = new StackPane();
//        layout2_3.getChildren().addAll(buttonSelectRoute);
//        scene2_3 = new Scene(layout2_3, 700, 500);
//
//        //Scene 3
//        Button  buttonLevelUp= new Button("Next Level");
//        buttonLevelUp.setOnAction(e -> window.setScene(scene4));
//
//
//        StackPane layout3 = new StackPane();
//        layout3.getChildren().addAll(buttonLevelUp);
//        scene3 = new Scene(layout3, 700, 500);
//
//        //Scene 4
//        Button buttonEndGame= new Button("End Game");
//        buttonEndGame.setOnAction(e -> closeProgram());
//
//
//        StackPane layout4 = new StackPane();
//        layout4.getChildren().addAll(buttonEndGame);
//        scene4 = new Scene(layout4, 700, 500);
//
//        window.setScene(scene1);
//        window.setTitle("City Walk");
//        window.show();
//    }
//
//    private void closeProgram(){
//        System.out.println("Save Game Results");
//        window.close();
//    }
//
//}