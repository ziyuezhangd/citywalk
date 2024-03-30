package groupsix.citywalk.maingame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EducationBox {
    public static void display(String message){
        Stage window = new Stage();

        //Blocks user interaction until this window closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Educational Pop");
        window.setMinWidth(250);

        Label label1 = new Label();
        label1.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();




    }
}
