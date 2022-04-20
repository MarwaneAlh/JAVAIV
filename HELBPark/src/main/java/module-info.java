module view.helbpark {
    requires javafx.controls;
    requires javafx.fxml;

    opens view.helbpark to javafx.fxml;
    exports helbpark.view;
}
