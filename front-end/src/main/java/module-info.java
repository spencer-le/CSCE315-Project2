module csce315_09g.frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens csce315_09g.frontend to javafx.fxml;
    exports csce315_09g.frontend;
}