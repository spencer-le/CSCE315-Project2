package theAlleyPOS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

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

    @javafx.fxml.FXML
    public void handleSnowStrawberryLuluClick(ActionEvent actionEvent) {
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
