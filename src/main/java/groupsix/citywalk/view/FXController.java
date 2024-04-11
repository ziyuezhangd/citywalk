package groupsix.citywalk.view;
import groupsix.citywalk.maingame.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

public class FXController {
    @FXML
    TextField nameTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {

        String username = (String) nameTextField.getText();

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML


    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2_3.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene3.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene4.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
//--------------------------------------------
//    @FXML
//    private TextField nameInput;
//
//    @FXML
//    private ImageView backgroundImage;
//
//    // Method to handle the Enter Game button action
//    @FXML
//    private void onEnterGame(MouseEvent event) {
//        try {
//            // Load Scene 2 FXML (assuming it is also converted to FXML)
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/view/CityWalkScene2.fxml"));
//            Parent root = loader.load();
//
//            // Get current stage from the event source
//            Stage stage = (Stage) nameInput.getScene().getWindow();
//
//            // Set new scene to stage and show
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Initialize method if needed
//    @FXML
//    private void initialize() {
//        // Optional: Any initialization for scene 1 components
//    }
//}
//
