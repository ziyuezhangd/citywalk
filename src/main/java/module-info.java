module groupsix.citywalk {
    requires javafx.controls;
    requires javafx.fxml;


//  opens groupsix.citywalk to javafx.fxml;
    exports groupsix.citywalk.maingame;
    opens groupsix.citywalk.maingame to javafx.fxml;
    exports groupsix.citywalk.model;
    opens groupsix.citywalk.model to javafx.fxml;
    exports groupsix.citywalk.util;
    opens groupsix.citywalk.util to javafx.fxml;
    exports groupsix.citywalk.controller;
    opens groupsix.citywalk.controller to javafx.fxml;
    exports groupsix.citywalk.service;
    opens groupsix.citywalk.service to javafx.fxml;
}