module group6.citywalk {
    requires javafx.controls;
    requires javafx.fxml;


    opens group6.citywalk to javafx.fxml;
    exports group6.citywalk;
}