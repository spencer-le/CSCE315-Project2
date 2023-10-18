package theAlleyPOS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theAlleyPOS.DatabaseHelper;
import theAlleyPOS.model.Item;

import java.io.IOException;
import java.util.List;

/**
 * @author Sebastian Oberg
 */
public class InventoryController {
    /**
     * Lines 25 through 42 create the required buttons, table values, and text fields.
     */
    public TableColumn inventoryCountColumn;
    public TextField itemCountField;
    public TextField itemIdField;
    public TableColumn idColumn;
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

    public TableColumn<Item, Integer> deleteColumn;


    /**
     * This initialize function puts the correct values into each created column, along with a delete button to use when
     * removing an item from inventory. This table is synced with the database, so when it is updated, the database is too.
     * The overridden updateItem function is what does this syncing, and it uses a DatabaseHelper
     */
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        inventoryCountColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("inventoryCount"));
        refreshTable();
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        Item itemToDelete = getTableView().getItems().get(getIndex());
                        DatabaseHelper dbHelper = new DatabaseHelper();
                        dbHelper.deleteItem(itemToDelete.getId());
                        refreshTable();
                    });

                    deleteButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent; -fx-border-width: 0px;");

                    setGraphic(deleteButton);
                }
            }
        });

        inventoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Item selectedItem = (Item) newSelection;
                itemIdField.setText(String.valueOf(selectedItem.getId()));
                itemNameField.setText(selectedItem.getItemName());
                itemPriceField.setText(String.valueOf(selectedItem.getPrice()));
                itemCountField.setText(String.valueOf(selectedItem.getInventoryCount()));
            }
        });

    }

    /**
     * This handleAddItem function allows you to add new items into the database and table, given you know their name,
     * price, id, and count, using a DatabaseHelper
     * @param actionEvent
     */
    @FXML
    public void handleAddItem(ActionEvent actionEvent) {
        int itemId = Integer.parseInt(itemIdField.getText());
        String itemName = itemNameField.getText();
        double itemPrice = Double.parseDouble(itemPriceField.getText());
        int itemCount = Integer.parseInt(itemCountField.getText());

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.addItem(new Item(itemId, itemName, itemPrice, itemCount));

        refreshTable();

        itemIdField.clear();
        itemNameField.clear();
        itemPriceField.clear();
        itemCountField.clear();
    }

    /**
     * This handleUpdateItem function allows you to update the price or count of the selected item in the database, using
     * a DatabaseHelper
     * @param actionEvent
     */
    @FXML
    public void handleUpdateItem(ActionEvent actionEvent) {
        int itemId = Integer.parseInt(itemIdField.getText());
        String itemName = itemNameField.getText();
        double itemPrice = Double.parseDouble(itemPriceField.getText());
        int itemCount = Integer.parseInt(itemCountField.getText());

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.updateItemInDatabase(new Item(itemId, itemName, itemPrice, itemCount));

        refreshTable();

        itemIdField.clear();
        itemNameField.clear();
        itemPriceField.clear();
        itemCountField.clear();
    }

    /**
     * The refresh table function accessed the database through a DatabaseHelper and updates the table with the current
     * available items
     */
    private void refreshTable() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ObservableList<Item> items = FXCollections.observableArrayList(dbHelper.fetchItems());
        inventoryTable.setItems(items);
    }


    /**
     * The handleHomeButton function sends the user back to the manager time clock screen.
     * @param actionEvent
     */
    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        loadManagerTimeClockScreen(actionEvent);
    }

    /**
     * This loadManagerTimeClockScreen function changes the current scene and stage from inventory controller to the
     * manager time clock screen using FXML
     * @param event
     */
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
