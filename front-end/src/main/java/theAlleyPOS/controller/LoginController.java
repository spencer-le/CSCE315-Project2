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
    // "jdbc:postgresql://csce-315-db.engr.tamu.edu/sthomas_demo" demo url
    // "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db" probably our url...
    //Building the connection
    Connection conn = null;
     try {
        //Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(
                "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db",
                "csce315_909_gshields432", "password");
        //TODO: ensure that plaintext isn't in the user/pass. maybe put in another file?
        // can we make a new user on the db that just has read-only access to login info and that way
        // we dont have to use one of our login's for this?
    } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
    }//end try catch
    try{
        //create a statement object
        Statement stmt = conn.createStatement();
        //create an SQL statement
        String sqlStatement = "SELECT name FROM cashiers"; //test statement to ensure connection works
        //send statement to DBMS
        ResultSet result = stmt.executeQuery(sqlStatement);

        //OUTPUT
        System.out.println("Select Names from Cashier Table");
        System.out.println("______________________________________");
        while (result.next()) {
            System.out.println(result.getString("name"));
        }
    } catch (Exception e){
        System.out.println("Error accessing Database.");
    }
     System.out.println("Opened database successfully");
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
            if(!IsValidID(int id)){ //if the id is NOT VALID
                clear entry
                display error text
                break/dont proceed
            }
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