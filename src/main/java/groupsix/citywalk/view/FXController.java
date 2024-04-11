package groupsix.citywalk.view;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FXController {

    @FXML
    private TextField nameInput;

    @FXML
    private ImageView backgroundImage;

    // Method to handle the Enter Game button action
    @FXML
    private void onEnterGame(MouseEvent event) {
        try {
            // Load Scene 2 FXML (assuming it is also converted to FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupsix/citywalk/view/CityWalkScene2.fxml"));
            Parent root = loader.load();

            // Get current stage from the event source
            Stage stage = (Stage) nameInput.getScene().getWindow();

            // Set new scene to stage and show
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Initialize method if needed
    @FXML
    private void initialize() {
        // Optional: Any initialization for scene 1 components
    }
}

