package theAlleyPOS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theAlleyPOS.model.Session;
import theAlleyPOS.DatabaseHelper;
import theAlleyPOS.model.Item;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.binding.Bindings;

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
    private TableView<Item> orderTable;

    @FXML
    private TableColumn<Item, Double> priceColumn;

    private SimpleDoubleProperty totalProperty = new SimpleDoubleProperty(0.0);

    @FXML
    private Label totalLabel;

    @FXML
    public void handleSnowStrawberryLuluClick(ActionEvent actionEvent) { addItemToOrder("Snow Strawberry Lulu"); }

    @FXML
    public void handleOrangeLuluClick(ActionEvent actionEvent) {
        addItemToOrder("Orange Lulu");
    }

    @FXML
    public void handleSnowVelvetPeachClick(ActionEvent actionEvent) { addItemToOrder("Snow Velvet Peach Oolong Tea"); }

    @FXML
    public void handleSnowVelvetLatteClick(ActionEvent actionEvent) { addItemToOrder("Snow Velvet Brown Sugar Latte"); }

    @FXML
    public void handleHojichaLatteClick(ActionEvent actionEvent) { addItemToOrder("Hojicha Peach Oolong Latte"); }

    @FXML
    public void handleMatchaLatteClick(ActionEvent actionEvent) { addItemToOrder("Matcha Peach Oolong Latte"); }

    @FXML
    public void handleRoyalTeaOriginalClick(ActionEvent actionEvent) { addItemToOrder("Royal No.9 Tea"); }

    @FXML
    public void handleAssamTeaOriginalClick(ActionEvent actionEvent) { addItemToOrder("Assam Black Tea"); }

    @FXML
    public void handlePeachOolongTeaClick(ActionEvent actionEvent) { addItemToOrder("Peach Oolong Tea"); }

    @FXML
    public void handleRoyalTeaPremiumClick(ActionEvent actionEvent) { addItemToOrder("Royal No.9 Tea"); }

    @FXML
    public void handleAssamTeaPremiumClick(ActionEvent actionEvent) { addItemToOrder("Assam Black Tea"); }

    @FXML
    public void handleGardenMilkTeaClick(ActionEvent actionEvent) { addItemToOrder("Garden Milk Tea"); }

    @FXML
    public void handleNorthernLightsClick(ActionEvent actionEvent) { addItemToOrder("Northern Lights"); }

    @FXML
    public void handleMorningDawnClick(ActionEvent actionEvent) { addItemToOrder("Morning Dawn"); }

    @FXML
    public void handleDeeriocaMilkClick(ActionEvent actionEvent) { addItemToOrder("Brown Sugar Deerioca Fresh Milk"); }

    @FXML
    public void handleCocoaDeeriocaMilkClick(ActionEvent actionEvent) { addItemToOrder("Cocoa Brown Sugar Deerioca Fresh Milk"); }

    @FXML
    public void handleMatchaDeeriocaMilkClick(ActionEvent actionEvent) { addItemToOrder("Matcha Brown Sugar Deerioca Fresh Milk"); }

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
        completeOrder();
    }

    @FXML
    public void handleCardClick(ActionEvent actionEvent) {
        completeOrder();
    }

    private void completeOrder() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        for (Item item : orderedItems) {
            dbHelper.decrementItemInventory(item.getItemName());
        }
        orderedItems.clear();
        totalProperty.set(0.0);
    }

    private ObservableList<Item> orderedItems = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Item, String> itemColumn;

    @FXML
    private TableColumn<Item, Integer> deleteColumn;

    @FXML
    private void initialize() {
        orderTable.setItems(orderedItems);
        totalLabel.textProperty().bind(Bindings.format("Total: $%.2f", totalProperty));
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setMaxWidth(Double.MAX_VALUE);
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setAlignment(Pos.CENTER);
                    deleteButton.setOnAction(event -> {
                        Item itemToDelete = getTableView().getItems().get(getIndex());
                        totalProperty.set(totalProperty.get() - itemToDelete.getPrice());
                        orderedItems.remove(itemToDelete);
                    });
                    setGraphic(deleteButton);
                }
            }
        });
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

    private void addItemToOrder(String itemName) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        Item item = dbHelper.getItemByName(itemName);
        if (item != null) {
            orderedItems.add(item);
            totalProperty.set(totalProperty.get() + item.getPrice());
        }
        else {
            System.out.println("Failed to fetch item details for " + itemName);
        }
    }
}
