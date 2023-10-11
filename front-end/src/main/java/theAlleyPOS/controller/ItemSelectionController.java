package theAlleyPOS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class ItemSelectionController {
    @javafx.fxml.FXML
    private Button btnSnowStrawberryLulu;
    @javafx.fxml.FXML
    private Button btnOrangeLulu;
    @javafx.fxml.FXML
    private Button btnSnowVelvetPeach;
    @javafx.fxml.FXML
    private Button btnSnowVelvetLatte;
    @javafx.fxml.FXML
    private Button btnHojichaLatte;
    @javafx.fxml.FXML
    private Button btnMatchaLatte;
    @javafx.fxml.FXML
    private Button btnRoyalTeaOriginal;
    @javafx.fxml.FXML
    private Button btnAssamTeaOriginal;
    @javafx.fxml.FXML
    private Button btnPeachOolongTea;
    @javafx.fxml.FXML
    private Button btnRoyalTeaPremium;
    @javafx.fxml.FXML
    private Button btnAssamTeaPremium;
    @javafx.fxml.FXML
    private Button btnGardenMilkTea;
    @javafx.fxml.FXML
    private Button btnNorthernLights;
    @javafx.fxml.FXML
    private Button btnMorningDawn;
    @javafx.fxml.FXML
    private Button btnDeeriocaMilk;
    @javafx.fxml.FXML
    private Button btnCocoaDeeriocaMilk;
    @javafx.fxml.FXML
    private Button btnMatchaDeeriocaMilk;
    @javafx.fxml.FXML
    private ListView orderList;
    @javafx.fxml.FXML
    private Button btnPearls;
    @javafx.fxml.FXML
    private Button btnSnowVelvet;
    @javafx.fxml.FXML
    private ComboBox cmbSweetness;
    @javafx.fxml.FXML
    private ComboBox cmbIceLevel;
    @javafx.fxml.FXML
    private Button btnCash;
    @javafx.fxml.FXML
    private Button btnCard;
    @javafx.fxml.FXML
    private Button btnHome;
    @javafx.fxml.FXML
    private ComboBox cmbCoupon;
    public void UpdateDB(String item_name){
        Connection conn = null;
        try {
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db",
                    "csce315_909_gshields432", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = "UPDATE items SET inventory_count = inventory_count - 1 " +
                    "WHERE item_name = '" + item_name + "'"; //test statement to ensure connection works
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
    @javafx.fxml.FXML
    public void handleSnowStrawberryLuluClick(ActionEvent actionEvent) {
        UpdateDB("Snow Strawberry Lulu");
    }

    @javafx.fxml.FXML
    public void handleOrangeLuluClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSnowVelvetPeachClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSnowVelvetLatteClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleHojichaLatteClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleMatchaLatteClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRoyalTeaOriginalClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleAssamTeaOriginalClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handlePeachOolongTeaClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRoyalTeaPremiumClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleAssamTeaPremiumClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleGardenMilkTeaClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleNorthernLightsClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleMorningDawnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleCocoaDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleMatchaDeeriocaMilkClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handlePearlsClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSnowVelvetClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSweetnessChange(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleIceLevelChange(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleCashClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleCardClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleHomeClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/EmployeeTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) btnHome.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load another screen.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void handleCouponChange(ActionEvent actionEvent) {
    }
}
