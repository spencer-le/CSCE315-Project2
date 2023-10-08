module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;


    opens theAlleyPOS to javafx.fxml;
    exports theAlleyPOS;
}