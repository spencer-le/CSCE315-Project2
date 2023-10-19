package theAlleyPOS.controller;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theAlleyPOS.model.Item;
import theAlleyPOS.DatabaseHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * @author Sebastian Oberg, Roshan Tayab
 */
public class AnalyticsController {
    /**
     * Lines 34 through 63 create the table buttons, columns, and tabs which will show the analytics
     */
    public TextField minimumAmountField;
    public TableColumn columnItemId;
    public TableColumn columnItemName;
    public TableColumn columnItemAmount;
    public Button btnLoadItems;
    @FXML
    public Button btnLoadSales;
    @FXML
    private Button homeButton1;
    @FXML
    private Button homeButton2;
    @FXML
    private Button homeButton3;
    @FXML
    private Button homeButton4;
    @FXML
    private Button btnLoadExcess;
    @FXML
    private TableView tableViewSalesReport;
    @FXML
    private DatePicker beginDateSalesReport;
    @FXML
    private DatePicker endDateSalesReport;
    @FXML
    private TableView tableViewExcess;
    @FXML
    private DatePicker startDatePickerExcess;

    @FXML
    private TableView tableViewSalesTogether;
    @FXML
    private DatePicker beginWST;
    @FXML
    private DatePicker endWST;
    @FXML
    private TableView tableViewRestockReport;

    /**
     * This initialize function creates the columns for the table and names them
     */
    @FXML
    public void initialize() {
        columnItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        columnItemAmount.setCellValueFactory(new PropertyValueFactory<>("inventoryCount"));
    }

    /**
     * The loadSalesReportByDate function organizes and displays the sales in between dates
     */
    public void loadSalesReportByDate() {
        LocalDate beginDate = beginDateSalesReport.getValue();
        LocalDate endDate = endDateSalesReport.getValue();
        //TODO: add support for getting the hour and minutes?

        if (beginDate == null || endDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Please select both a start and an end date.");
            alert.showAndWait();
            return;
        }

        // convert to timestamps for query
        // Convert LocalDate to java.util.Date
        java.util.Date beginUtilDate = java.util.Date.from(beginDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endUtilDate = java.util.Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Convert java.util.Date to java.sql.Timestamp
        Timestamp beginTimestamp = new Timestamp(beginUtilDate.getTime());
        Timestamp endTimestamp = new Timestamp(endUtilDate.getTime());


        DatabaseHelper dbHelper = new DatabaseHelper();
        List<Integer> ordersByDate = dbHelper.ordersByDate(beginTimestamp, endTimestamp);

        // go through order_items table, get list of item IDs
        List<Integer> itemIds = dbHelper.getItemIdsByOrderIds(ordersByDate);


        //TODO: using item IDs, sum up total sales
        Map<Integer, Integer> frequency = dbHelper.calculateItemFrequency(itemIds);

        // Fetching item names and prices based on itemIds
        Map<Integer, String> itemNameById = dbHelper.fetchItemNamesByIds(itemIds);
        Map<Integer, Double> itemPriceById = dbHelper.fetchItemPricesByIds(itemIds);

        // Setting up the columns for tableViewSalesReport
        TableColumn<Map.Entry<Integer, Integer>, Integer> itemIdColumn = new TableColumn<>("Item ID");
        itemIdColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());

        TableColumn<Map.Entry<Integer, Integer>, Integer> frequencyColumn = new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        TableColumn<Map.Entry<Integer, Integer>, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(p -> new SimpleStringProperty(itemNameById.get(p.getValue().getKey())));

        TableColumn<Map.Entry<Integer, Integer>, Double> revenueColumn = new TableColumn<>("Revenue");
        revenueColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getValue() * itemPriceById.get(p.getValue().getKey())).asObject());

        tableViewSalesReport.getColumns().setAll(itemIdColumn, itemNameColumn, frequencyColumn, revenueColumn);

        ObservableList<Map.Entry<Integer, Integer>> data = FXCollections.observableArrayList(frequency.entrySet());
        tableViewSalesReport.setItems(data);


    }
    public void handleLoadSalesClick(ActionEvent actionEvent) {
        loadSalesReportByDate();
    }

    public void whatSalesTogether() {
        LocalDate beginDate = beginWST.getValue();
        LocalDate endDate = endWST.getValue();

        if (beginDate == null || endDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Please select both a start and an end date.");
            alert.showAndWait();
            return;
        }

        // convert to timestamps for query
        // Convert LocalDate to java.util.Date
        java.util.Date beginUtilDate = java.util.Date.from(beginDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endUtilDate = java.util.Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Convert java.util.Date to java.sql.Timestamp
        Timestamp beginTimestamp = new Timestamp(beginUtilDate.getTime());
        Timestamp endTimestamp = new Timestamp(endUtilDate.getTime());


        DatabaseHelper dbHelper = new DatabaseHelper();
        List<Integer> ordersByDate = dbHelper.ordersByDate(beginTimestamp, endTimestamp);
        List<DatabaseHelper.ItemPairFrequency> itemPairFrequencies = dbHelper.findItemPairFrequencies(ordersByDate.get(0), ordersByDate.get(ordersByDate.size() - 1));

        ObservableList<Map.Entry<Integer, Integer>> observableData = FXCollections.observableArrayList();
        for (DatabaseHelper.ItemPairFrequency pairFrequency : itemPairFrequencies) {
            Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(pairFrequency.getItem1(), pairFrequency.getItem2());
            observableData.add(entry);
        }

        // Define and set up your columns
        TableColumn<Map.Entry<Integer, Integer>, Integer> itemIdColumn = new TableColumn<>("Item 1");
        itemIdColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());

        TableColumn<Map.Entry<Integer, Integer>, Integer> itemNameColumn = new TableColumn<>("Item 2");
        itemNameColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        TableColumn<Map.Entry<Integer, Integer>, Integer> frequencyColumn = new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        frequencyColumn.setSortType(TableColumn.SortType.DESCENDING); // Specify sorting order
        frequencyColumn.setSortable(true);

        tableViewSalesTogether.getColumns().setAll(itemIdColumn, itemNameColumn, frequencyColumn);
        tableViewSalesTogether.setItems(observableData);
    }
    public void handleWST(ActionEvent actionEvent) {whatSalesTogether();}


    /**
     * The loadRestockItems function takes in a minimum number from the user, and returns all items whose inventory is
     * less than this number, using a DatabaseHelper.
     */
    public void loadRestockItems() {
        int minAmount;
        try {
            String userInput = minimumAmountField.getText().trim();
            if (userInput.isEmpty()) {
                throw new NumberFormatException("Error: Input field is empty");
            }
            minAmount = Integer.parseInt(userInput);

            if (minAmount < 0) {
                throw new NumberFormatException("Error: Number cannot be negative");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Invalid Input. Please try again.");
            alert.showAndWait();
            return;
        }
        DatabaseHelper dbHelper = new DatabaseHelper();
        List<Item> filteredItems = dbHelper.fetchItemsBelowInventoryCount(minAmount);
        ObservableList<Item> observableList = FXCollections.observableArrayList(filteredItems);
        tableViewRestockReport.setItems(observableList);
    }

    /**
     * The loadManagerTimeClockScreen function changes scenes to the home screen for the current manager.\
     * @param event
     */
    private void loadManagerTimeClockScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/theAlleyPOS/ManagerTimeClock.fxml"));
            Scene anotherScene = new Scene(fxmlLoader.load());

            // Getting the current stage from any component (assuming all are on the same stage)
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Switching scene
            currentStage.setScene(anotherScene);
        } catch (IOException e) {
            System.out.println("Failed to load manager time clock screen.");
            e.printStackTrace();
        }
    }

    /**
     * The handleHomeButton function uses FXML to send the user back to the home screen when the button is pressed
     * @param actionEvent
     */
    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        loadManagerTimeClockScreen(actionEvent);
    }

    /**
     * The handleLoadItemsClick function also uses FXML to load the items which need to be restocked.
     * @param actionEvent
     */
    @FXML
    public void handleLoadItemsClick(ActionEvent actionEvent) {
        loadRestockItems();
    }
    @FXML
    private void handleLoadExcessClick(ActionEvent event) {
        loadExcessReport();
    }
    @FXML
    public void loadExcessReport(){
        LocalDate beginning_date = startDatePickerExcess.getValue();
        if (beginning_date == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Please select both a valid start date");
            alert.showAndWait();
            return;
        }
        Timestamp beginning_date_timestamp = Timestamp.valueOf(beginning_date.atStartOfDay());
        DatabaseHelper dbHelper = new DatabaseHelper();
        List<Item> ExcessItems = dbHelper.fetchItems();
        // if the item is NOT in excess, remove it

        ExcessItems.removeIf(item -> !dbHelper.inExcessSinceDate(beginning_date_timestamp, item.getId()));

        TableColumn<Item, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewExcess.getColumns().addAll(idColumn, nameColumn);
        ObservableList<Item> observableList = FXCollections.observableArrayList(ExcessItems);
        tableViewExcess.setItems(observableList);
    }


}