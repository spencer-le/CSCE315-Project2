package theAlleyPOS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import theAlleyPOS.model.Session;
import theAlleyPOS.model.UserRole;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField employeeID;

    @FXML
    private Button oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton,
            deleteButton, enterButton;

    public void handleLogin(ActionEvent actionEvent) {
        String input = employeeID.getText();

        // Check for valid input length
        if (input == null || input.length() != 4 || input.equals("----")) {
            employeeID.setText("----");
            System.out.println("Invalid input length! Try again.");
            return; // Exit early
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
            employeeID.setText("----");
            System.out.println("Incorrect Input! Try again.");
        }
    }

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