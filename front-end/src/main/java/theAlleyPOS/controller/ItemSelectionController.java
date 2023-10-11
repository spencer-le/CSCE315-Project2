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

    @FXML
    public void handleSnowStrawberryLuluClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleOrangeLuluClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSnowVelvetPeachClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSnowVelvetLatteClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleHojichaLatteClick(ActionEvent actionEvent) {
    }

    @FXML
    public void handleMatchaLatteClick(ActionEvent actionEvent) {
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
