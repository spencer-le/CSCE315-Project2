package theAlleyPOS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import theAlleyPOS.model.Session;
import theAlleyPOS.model.UserRole;

import java.io.IOException;

/**
 * @author Sebastian Oberg, Grant Shields, Blake Dugan
 */
public class LoginController {
    /**
     * Lines 23 through 29 create the required buttons for the number pad on the login screen
     */
    @FXML
    private TextField employeeID;

    @FXML
    private Button oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton,
            deleteButton, enterButton;

    /**
     * The handleLogin function uses a DatabaseHelper and UserRole to determine if the input password matches one listed
     * in the database. If it does, then employees and managers are sent to their respective screens.
     * @param actionEvent
     */
    public void handleLogin(ActionEvent actionEvent) {
        String input = employeeID.getText();

        try {
            // Check for valid input length
            if (input == null || input.length() != 4 || input.equals("----")) {
                throw new IllegalArgumentException("Invalid input length! Try again.");
            }

            theAlleyPOS.DatabaseHelper dbHelper = new theAlleyPOS.DatabaseHelper();
            UserRole role = dbHelper.validateUser(input);

            if (role == UserRole.MANAGER) {
                Session.setManager(true);
                loadManagerTimeClockScreen(actionEvent);
            } else if (role == UserRole.EMPLOYEE) {
                Session.setManager(false);
                loadEmployeeTimeClockScreen(actionEvent);
            } else {
                throw new IllegalArgumentException("Incorrect Input! Try again.");
            }

        } catch (IllegalArgumentException e) {
            employeeID.setText("----");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * The handleNumberButton reads the users selected button. When Delete is pressed, it removes one number if there are
     * any to delete. When enter is pressed, it calls the handleLogin function. If a number is pressed then it is added
     * to the screen.
     * @param actionEvent
     */
    @FXML
    public void handleNumberButton(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "Del":
                String currentText = employeeID.getText();
                if (!currentText.isEmpty()) {
                    employeeID.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;

            case "Enter":
                // Trigger login
                handleLogin(actionEvent);
                break;

            default:
                // Append the number to the TextField
                if(employeeID.getText().equals("----")) {
                    employeeID.setText(""); // Clear placeholder if it's the default
                }
                employeeID.appendText(buttonText);
                break;
        }
    }

    /**
     * This loadEmployeeTimeClockScreen function changes the current scene and stage from login controller to the
     * manager time clock screen using FXML
     * @param event
     */
    private void loadEmployeeTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/CashierTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) employeeID.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load another screen.");
            e.printStackTrace();
        }
    }

    /**
     * This loadManagerTimeClockScreen function changes the current scene and stage from login controller to the
     * manager time clock screen using FXML
     * @param event
     */
    private void loadManagerTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ManagerTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) employeeID.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load another screen.");
            e.printStackTrace();
        }
    }
}