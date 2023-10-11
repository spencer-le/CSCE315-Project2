package theAlleyPOS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theAlleyPOS.DatabaseHelper;
import theAlleyPOS.model.Item;

import java.io.IOException;
import java.util.List;

public class InventoryController {
    @FXML
    private TableView inventoryTable;
    @FXML
    private TableColumn itemColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private Button inventoryHomeButton;

    @FXML
    public void handleAddItem(ActionEvent actionEvent) {
        // TODO
    }

    @FXML
    public void handleUpdateItem(ActionEvent actionEvent) {
        // TODO
    }

    public void addItem(String itemName, double price, int inventoryCount) {
        // TODO
    }

    public void updateItem(int id, String itemName, double price, int inventoryCount) {
        // TODO
    }

    @FXML
    private void initialize() {
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        refreshTable();
    }

    private void refreshTable() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ObservableList<Item> items = FXCollections.observableArrayList(dbHelper.fetchItems());
        inventoryTable.setItems(items);
    }


    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        loadManagerTimeClockScreen(actionEvent);
    }

    private void loadManagerTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ManagerTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage
            Stage currentStage = (Stage) inventoryHomeButton.getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load manager time clock screen.");
            e.printStackTrace();
        }
    }
}
