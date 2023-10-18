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
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import theAlleyPOS.DatabaseHelper;
import theAlleyPOS.model.*;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.binding.Bindings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
Lines 30 through 80 create the buttons and boxes which will be displayed on the item selection screen, and lines 81
through 94 initialize a database helper, table, column, price, and label variables.
 */
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
    private TableView<Item> otherTable;
    @FXML
    private TableColumn<Item, String> cartItemColumn;
    @FXML
    private TableColumn<Orderable, String> otherItemColumn;

    private DatabaseHelper dbHelper = new DatabaseHelper();

    @FXML
    private TableView<Orderable> orderTable;

    @FXML
    private TableColumn<Orderable, Double> priceColumn;

    private SimpleDoubleProperty totalProperty = new SimpleDoubleProperty(0.0);

    @FXML
    private Label totalLabel;

    /*
    Lines 99 through 149 use the addItemToOrder function to handle the FXML commands for when item buttons are pressed
     */
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

    /*
    Lines 154 through 174 use the function addModifierToOrder to handle the FXML commands for when item modifiers are pressed
     */
    @FXML
    public void handlePearlsClick(ActionEvent actionEvent) {
        addModifierToOrder("Pearls");
    }

    @FXML
    public void handleSnowVelvetClick(ActionEvent actionEvent) {
        addModifierToOrder("Snow Velvet");
    }

    @FXML
    public void handleSweetnessChange(ActionEvent actionEvent) {
        String selectedSweetness = (String) cmbSweetness.getSelectionModel().getSelectedItem();
        addModifierToOrder(selectedSweetness);
    }

    @FXML
    public void handleIceLevelChange(ActionEvent actionEvent) {
        String selectedIceLevel = (String) cmbIceLevel.getSelectionModel().getSelectedItem();
        addModifierToOrder(selectedIceLevel);
    }

    /*
    Lines 179 through 188 call completeOrder as soon as the cash or card buttons are pressed,
    which are determined with FXML
     */
    @FXML
    public void handleCashClick(ActionEvent actionEvent) {
        completeOrder();
    }

    @FXML
    public void handleCardClick(ActionEvent actionEvent) {
        completeOrder();
    }
    // private List<Modifier> selectedModifiers = new ArrayList<>();

    /*
    The completeOrder function uses DatabaseHelper from the model folder to access and alter the database.
    It creates a new order using the local time and total cost, and removes the ordered items from the inventory.
     */
    private void completeOrder() {
        // Extract the int value from newID
        int orderId = dbHelper.getNewOrderID().get();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Extract the double value from totalProperty
        double totalCost = totalProperty.get();

        Order newOrder = new Order(orderId, "Customer " + orderId, currentDateTime, totalCost);
        dbHelper.addOrder(newOrder);
        for (Orderable orderable : orderedItems) {
            if (orderable instanceof Item) {
                dbHelper.decrementItemInventory(orderable.getName());
                dbHelper.addToOrderedItems(newOrder.getId(), ((Item) orderable).getId());
            } else if (orderable instanceof Modifier) {
                dbHelper.decrementModifierInventory(orderable.getName());
            }
        }

        orderedItems.clear();
        totalProperty.set(0.0);
    }

    private ObservableList<Orderable> orderedItems = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Orderable, Integer> deleteColumn;

    /*
    The initialize function lays out the table values for the current order, and uses the overridden updateItem
    function to add items and modifiers to the screen as they are removed from the orderedItems list
     */
    @FXML
    private void initialize() {
        orderTable.setItems(orderedItems);
        totalLabel.textProperty().bind(Bindings.format("Total: $%.2f", totalProperty));

        cartItemColumn.setCellValueFactory(cellData -> {
            Orderable orderable = cellData.getValue();
            if (orderable instanceof Item) {
                return new SimpleStringProperty(((Item) orderable).getName());
            } else if (orderable instanceof Modifier) {
                return new SimpleStringProperty(((Modifier) orderable).getName());
            }
            return new SimpleStringProperty("");
        });

        priceColumn.setCellValueFactory(cellData -> {
            Orderable orderable = cellData.getValue();
            double price;
            if (orderable instanceof Item) {
                price = ((Item) orderable).getPrice();
            } else if (orderable instanceof Modifier) {
                price = ((Modifier) orderable).getPrice();
            } else {
                price = 0.0;
            }
            return new SimpleDoubleProperty(price).asObject();
        });

        initializeOtherTable();
        fetchAndDisplayItemsFromDatabase();

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
                        Orderable orderableToDelete = getTableView().getItems().get(getIndex());
                        if (orderableToDelete instanceof Item) {
                            Item itemToDelete = (Item) orderableToDelete;
                            totalProperty.set(totalProperty.get() - itemToDelete.getPrice());
                            orderedItems.remove(itemToDelete);
                        } else if (orderableToDelete instanceof Modifier) {
                            Modifier modifierToDelete = (Modifier) orderableToDelete;
                            totalProperty.set(totalProperty.get() - modifierToDelete.getPrice());
                            orderedItems.remove(modifierToDelete);
                        }
                    });
                    setGraphic(deleteButton);
                }
            }
        });
    }
    private void fetchAndDisplayItemsFromDatabase() {
        ObservableList<Item> items = FXCollections.observableArrayList();

        // Use the fetchItems method from your DatabaseHelper class
        List<Item> itemList = dbHelper.fetchOtherItems();

        if (itemList != null && !itemList.isEmpty()) {
            items.addAll(itemList);
        }

        otherTable.setItems(items);
    }

    private void initializeOtherTable() {
        otherItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        otherTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && otherTable.getSelectionModel().getSelectedItem() != null) {
                Item selectedItem = otherTable.getSelectionModel().getSelectedItem();
                addItemToOrder(selectedItem.getName());
            }
        });
    }

    /*
    The handleHomeClick function determines which time clock screen to return the user to, depending on if they are a
    manager or employee.
     */
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

    /*
    The loadEmployeeTimeClockScreen, and the loadManagerTimeClockScreen functions change scenes to the appropriate
    home screen for the respective employee or manager.
     */
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

    /*
    The addItemToOrder function uses the DatabaseHelper class to retrieve the item and its properties from the database,
    and updates the total price. The addModifierToOrder function under it does the same for modifiers
     */
    private void addItemToOrder(String itemName) {
        Item item = dbHelper.getItemByName(itemName);
        if (item != null) {
            orderedItems.add(item);
            totalProperty.set(totalProperty.get() + item.getPrice());
        }
        else {
            System.out.println("Failed to fetch item details for " + itemName);
        }
    }

    private void addModifierToOrder(String modifierName) {
        Modifier modifier = dbHelper.getModifierByName(modifierName);
        if (modifier != null) {
            orderedItems.add(modifier);
            totalProperty.set(totalProperty.get() + modifier.getPrice());
        } else {
            System.out.println("Failed to fetch modifier details for " + modifierName);
        }
    }
}
