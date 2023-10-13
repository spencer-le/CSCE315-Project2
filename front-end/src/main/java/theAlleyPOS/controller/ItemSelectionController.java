package theAlleyPOS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import theAlleyPOS.model.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ItemSelectionController {
    @FXML
    private Button btnSnowStrawberryLulu;
    @FXML
    private Button btnOrangeLulu;
    @FXML
    private Button btnSnowVelvetPeach;
    @FXML
    private Button btnSnowVelvetLatte;
    @FXML
    private Button btnHojichaLatte;
    @FXML
    private Button btnMatchaLatte;
    @FXML
    private Button btnRoyalTeaOriginal;
    @FXML
    private Button btnAssamTeaOriginal;
    @FXML
    private Button btnPeachOolongTea;
    @FXML
    private Button btnRoyalTeaPremium;
    @FXML
    private Button btnAssamTeaPremium;
    @FXML
    private Button btnGardenMilkTea;
    @FXML
    private Button btnNorthernLights;
    @FXML
    private Button btnMorningDawn;
    @FXML
    private Button btnDeeriocaMilk;
    @FXML
    private Button btnCocoaDeeriocaMilk;
    @FXML
    private Button btnMatchaDeeriocaMilk;
    @FXML
    private ListView orderList;
    @FXML
    private Button btnPearls;
    @FXML
    private Button btnSnowVelvet;
    @FXML
    private ComboBox cmbSweetness;
    @FXML
    private ComboBox cmbIceLevel;
    @FXML
    private Button btnCash;
    @FXML
    private Button btnCard;
    @FXML
    private Button btnHome;
    @FXML
    private ComboBox cmbCoupon;

    public void UpdateDB(String item_name){
        /*
        this function was originally made to update the database on click, but will be reformatted
        to read an order and update the DB one item (and its modifiers) at a time
         */
        Connection conn = null;
        try {
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db",
                    "csce315_909_gshields432", "password");
            //TODO replace user with current user, not just grant
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = "UPDATE items SET inventory_count = inventory_count - 1 " +
                    "WHERE item_name = '" + item_name + "'";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            try {
                conn.close(); //connection can be closed. all required data has been accessed.
                System.out.println("Connection Closed.");
            } catch(Exception e) {
                System.out.println("Connection NOT Closed.");
            }
        } catch (Exception e){
            System.out.println("Error accessing Database.");
        }
    }

    public void AddItemToOrder(String item_name){

    }
    @FXML
    public void handleSnowStrawberryLuluClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleOrangeLuluClick(ActionEvent actionEvent) {
        //UpdateDB("Orange Lulu");
    }

    @FXML
    public void handleSnowVelvetPeachClick(ActionEvent actionEvent) {
        //UpdateDB("Snow Velvet Peach Oolong Tea");
    }

    @FXML
    public void handleSnowVelvetLatteClick(ActionEvent actionEvent) {
        //UpdateDB("Snow Velvet Brown Sugar Latte");
    }

    @FXML
    public void handleHojichaLatteClick(ActionEvent actionEvent) {
        //UpdateDB("Hojicha Peach Oolong Latte");
    }

    @FXML
    public void handleMatchaLatteClick(ActionEvent actionEvent) {
        //UpdateDB("Matcha Peach Oolong Latte");
    }

    @FXML
    public void handleRoyalTeaOriginalClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleAssamTeaOriginalClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handlePeachOolongTeaClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleRoyalTeaPremiumClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleAssamTeaPremiumClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleGardenMilkTeaClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleNorthernLightsClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleMorningDawnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleCocoaDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleMatchaDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handlePearlsClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSnowVelvetClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSweetnessChange(ActionEvent actionEvent) {
    }

    @FXML
    public void handleIceLevelChange(ActionEvent actionEvent) {
    }

    @FXML
    public void handleCashClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleCardClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleHomeClick(ActionEvent actionEvent) {
        if (Session.isManager())
        {
            // Switch to manager timeClock
            loadManagerTimeClockScreen(actionEvent);
        }

        else {
            // Switch to customer timeClock
            loadEmployeeTimeClockScreen(actionEvent);
        }
    }

    @FXML
    public void handleCouponChange(ActionEvent actionEvent) {
    }

    private void loadEmployeeTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/CashierTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) btnHome.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load employee time clock screen.");
            e.printStackTrace();
        }
    }

    private void loadManagerTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ManagerTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) btnHome.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load manager time clock screen.");
            e.printStackTrace();
        }
    }
}
