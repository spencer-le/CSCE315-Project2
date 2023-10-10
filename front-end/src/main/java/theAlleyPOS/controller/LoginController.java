package theAlleyPOS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private boolean IsValidID(int ID){

    }
    @FXML
    private TextField employeeID;

    @FXML
    private Button oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton,
            deleteButton, enterButton;

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        // TODO: Handle login (Put logic here to process the entered ID)
        /*brainstorming:
            Each employee (managers & cashiers) has an ID in the database.
                Each ID should be 5 digits long (as opposed to 0,1,2... with default ID's)
            The 5 digit code entered into the LoginController is used to search for a match in the DB

            if(ID is valid)
        */

        // After validation, switch to next screen:
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/EmployeeTimeClock.fxml"));
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
                if(employeeID.getText().equals("------")) {
                    employeeID.setText(""); // Clear placeholder if it's the default
                }
                employeeID.appendText(buttonText);
                break;
        }
    }
}