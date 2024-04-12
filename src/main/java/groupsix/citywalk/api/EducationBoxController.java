package groupsix.citywalk.api;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EducationBoxController {
    @FXML
    private Label messageLabel;

    public void setMessage(String transportType) {
        String message = getMessageBasedOnTransportType(transportType);
        messageLabel.setText(message);
    }

    private String getMessageBasedOnTransportType(String transportType) {
        // 这里包含了根据交通方式确定消息文本的逻辑
        switch (transportType) {
            case "bike":
                return "Great choice! Biking is excellent for both your health and the environment.";
            case "bus":
                return "Good choice! Taking the bus reduces your carbon footprint and helps alleviate traffic.";
            case "walk":
                return "Wonderful! Walking is the best way to stay healthy and reduce environmental impact.";
            case "taxi":
                return "Consider other options! While convenient, taxis contribute more to urban congestion and pollution.";
            default:
                return "Please select a valid transport method.";
        }
    }


    @FXML
    private void handleClose() {
        // 获取当前场景的Stage并关闭它
        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }

}
