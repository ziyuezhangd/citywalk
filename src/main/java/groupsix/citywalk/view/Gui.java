package groupsix.citywalk.view;
import groupsix.citywalk.maingame.EducationBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Gui extends Application {
    //test below
    Stage window;
    Scene scene1, scene2_1, scene2_2, scene2_3,scene3, scene4;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage initialStage) throws Exception {
        this.window = initialStage;

        // Root layout pane
        StackPane rootLayout = new StackPane();
        // image from: https://pin.it/DKaZkhplq (last accessed on 9/4/2024)
        ImageView backgroundImage = new ImageView(Gui.class.getResource("/groupsix/citywalk/view/cityWalkImage.jpg").toExternalForm());
        backgroundImage.setFitWidth(650); // Match the scene width or as desired
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);

        // VBox for vertically aligning labels and button
        VBox centerBox = new VBox(10); // Spacing between elements
        centerBox.setAlignment(Pos.CENTER);

        // Welcome label
        Label welcome = new Label("Welcome to CityWalk!");
        welcome.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");


        // Prompt label
        Label namePrompt = new Label("Please enter your name:");


        // Name input field
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(200); // Set a max width for text field

        // Start game button
        Button buttonStartGame = new Button("Enter Game");

        // Set button action
        buttonStartGame.setOnAction(e -> {
            String playerName = nameInput.getText(); // Retrieve input from TextField
            window.setScene(scene2_3);
        });

        // Add all elements to VBox
        centerBox.getChildren().addAll(welcome, namePrompt, nameInput, buttonStartGame);

        // Add VBox and ImageView to root layout
        rootLayout.getChildren().addAll(backgroundImage, centerBox);

        // Create the scene with the root layout
        Scene scene1 = new Scene(rootLayout, 650, 500);

        // Set CSS stylesheet
        scene1.getStylesheets().add(Gui.class.getResource("Theme.css").toExternalForm());

        // Setup and show the stage
        window.setScene(scene1);
        window.setTitle("City Walk");
        window.show();
        //Scene 2.3
        Button buttonSelectRoute = new Button("Start Trip");
        buttonSelectRoute.setOnAction(e -> {
            EducationBox.display("Education message from Education to be added here");
            window.setScene(scene3);
        });

        GridPane layout2_3 = new GridPane();
        layout2_3.setPadding(new Insets(10,10,10,10));
        layout2_3.setVgap(8);
        layout2_3.setHgap(10);
        layout2_3.getChildren().addAll(buttonSelectRoute);
        scene2_3 = new Scene(layout2_3, 700, 500);

        //Scene 3
        Button  buttonLevelUp= new Button("Next Level");
        buttonLevelUp.setOnAction(e -> window.setScene(scene4));


        StackPane layout3 = new StackPane();
        layout3.getChildren().addAll(buttonLevelUp);
        scene3 = new Scene(layout3, 700, 500);

        //Scene 4
        Button buttonEndGame= new Button("End Game");
        buttonEndGame.setOnAction(e -> closeProgram());


        StackPane layout4 = new StackPane();
        layout4.getChildren().addAll(buttonEndGame);
        scene4 = new Scene(layout4, 700, 500);

        window.setScene(scene1);
        window.setTitle("City Walk");
        window.show();
    }

    private void closeProgram(){
        System.out.println("Save Game Results");
        window.close();
    }
}
