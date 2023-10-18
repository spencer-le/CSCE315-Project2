package theAlleyPOS.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class CashierTimeClockController {

    /*
    Line 17 creates all the required buttons for the cashier time clock (home page)
     */
    @FXML
    private Button clockInButton, breakInButton, clockOutButton, breakOutButton;

    /*
    The handleClockInOut function sends the user to the item selection screen if "clock in" is chosen, and sends
    them to the login screen if "clock out" is chosen.
     */
    @FXML
    public void handleClockInOut(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "Clock In":
                // Takes user to item selection screen
                loadItemSelectionScreen(actionEvent);
                break;

            case "Clock Out":
                // Takes user back to login screen
                loadLoginScreen(actionEvent);
                break;
        }
    }

    /*
    The handleBreakInOut function does the same thing as the handleClockInOut function, except "break in" is used in
    place of "clock in" and "break out" is used in the place of "clock out".
     */
    @FXML
    public void handleBreakInOut(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "Break In":
                // Takes user to item selection screen
                loadItemSelectionScreen(actionEvent);
                break;

            case "Break Out":
                // Takes user back to login screen
                loadLoginScreen(actionEvent);
                break;
        }
    }

    /*
    The loadLoginScreen function changes the FXML scene and stage to that of the login screen.
     */
    private void loadLoginScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/Login.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) clockInButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load login screen.");
            e.printStackTrace();
        }
    }

    /*
    The loadItemSelectionScreen function also uses FXML to change stages and scenes to the item selection screen
     */
    private void loadItemSelectionScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ItemSelection.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) clockInButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load item selection screen.");
            e.printStackTrace();
        }
    }
}
