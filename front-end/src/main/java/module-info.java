module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens theAlleyPOS to javafx.fxml;
    opens theAlleyPOS.controller to javafx.fxml;
    opens theAlleyPOS.model to javafx.base; // This line provides the needed access

    exports theAlleyPOS;
    exports theAlleyPOS.controller;
}
