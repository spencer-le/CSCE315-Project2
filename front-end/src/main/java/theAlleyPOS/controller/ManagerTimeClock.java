package theAlleyPOS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Sebastian Oberg
 */
public class ManagerTimeClock {
    /**
     * Lines 19 through 30 create the buttons for the manager to use on their time clock
     */
    @FXML
    private Button managerClockInButton;
    @FXML
    private Button managerBreakInButton;
    @FXML
    private Button managerClockOutButton;
    @FXML
    private Button managerBreakOutButton;
    @FXML
    private Button managerInventoryButton;
    @FXML
    private Button managerAnalyticsButton;

    /**
     * The handleClockInOut function sends the user to the item selection screen if "clock in" is chosen, and sends
     * them to the login screen if "clock out" is chosen.
     * @param actionEvent
     */
    @FXML
    public void handleManagerClockInOut(ActionEvent actionEvent) {
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

    /**
     * The handleBreakInOut function does the same thing as the handleClockInOut function, except "break in" is used in
     * place of "clock in" and "break out" is used in the place of "clock out".
     * @param actionEvent
     */
    @FXML
    public void handleManagerBreakInOut(ActionEvent actionEvent) {
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

    /**
     * The handleManagerInventoryAction function sends the user to the inventory screen
     * @param actionEvent
     */
    @FXML
    public void handleManagerInventoryAction(ActionEvent actionEvent) {
        loadInventoryScreen(actionEvent);
    }

    /**
     * The handleManagerAnalyticsAction function sends the user to the analytics screen
     * @param actionEvent
     */
    @FXML
    public void handleManagerAnalyticsAction(ActionEvent actionEvent) {
        loadAnalyticsScreen(actionEvent);
    }

    /**
     * The loadLoginScreen function changes the FXML scene and stage to that of the login screen.
     * @param event
     */
    private void loadLoginScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/Login.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) managerClockInButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load login screen.");
            e.printStackTrace();
        }
    }
    /**
     * The loadAnalyticsScreen function also uses FXML to change stages and scenes to the analytics screen
     * @param event
     */
    private void loadAnalyticsScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/Analytics.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) managerAnalyticsButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load analytics screen.");
            e.printStackTrace();
        }
    }

    /**
     * The loadItemSelectionScreen function also uses FXML to change stages and scenes to the item selection screen
     * @param event
     */
    private void loadItemSelectionScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ItemSelection.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) managerClockInButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load item selection screen.");
            e.printStackTrace();
        }
    }
    /**
     * The loadInventoryScreen function also uses FXML to change stages and scenes to the inventory screen
     * @param event
     */
    private void loadInventoryScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/Inventory.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage using any of the controls
            Stage currentStage = (Stage) managerInventoryButton.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Failed to load inventory screen.");
            e.printStackTrace();
        }
    }
}