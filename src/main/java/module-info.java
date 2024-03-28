module group6.citywalk {
    requires javafx.controls;
    requires javafx.fxml;


    opens groupsix.citywalk to javafx.fxml;
    exports groupsix.citywalk;
    exports maingame;
    opens maingame to javafx.fxml;
}