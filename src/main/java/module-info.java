module group6.citywalk {
    requires javafx.controls;
    requires javafx.fxml;


    opens groupsix.citywalk to javafx.fxml;
    exports groupsix.citywalk;
    exports groupsix.citywalk.maingame;
    opens groupsix.citywalk.maingame to javafx.fxml;
    exports groupsix.citywalk.model;
    opens groupsix.citywalk.model to javafx.fxml;
    exports groupsix.citywalk.util;
    opens groupsix.citywalk.util to javafx.fxml;
    exports groupsix.citywalk.api;
    opens groupsix.citywalk.api to javafx.fxml;
    exports groupsix.citywalk.view;
    opens groupsix.citywalk.view to javafx.fxml;
}