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
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

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
//        Timestamp beginTimestamp = new Timestamp(beginUtilDate.getTime());
//        Timestamp endTimestamp = new Timestamp(endUtilDate.getTime());

        Timestamp beginTimestamp = Timestamp.valueOf("2023-10-17 17:00:00"); // Replace with your desired start timestamp
        Timestamp endTimestamp = Timestamp.valueOf("2023-10-17 21:09:15.31785");

        // Print LocalDate and Timestamp values
//        System.out.println("Begin LocalDate: " + beginDate);
//        System.out.println("End LocalDate: " + endDate);
//        System.out.println("Begin Timestamp: " + beginTimestamp);
//        System.out.println("End Timestamp: " + endTimestamp);

        DatabaseHelper dbHelper = new DatabaseHelper();
        List<Integer> ordersByDate = dbHelper.ordersByDate(beginTimestamp, endTimestamp);

        //TODO: go through order_items table, get list of item IDs
        List<Integer> itemIds = dbHelper.getItemIdsByOrderIds(ordersByDate);
        for (Integer orderId : itemIds) {
            System.out.println("Order ID: " + orderId);
        }

        //TODO: using item IDs, sum up total sales
        Map<Integer, Integer> frequency = dbHelper.calculateItemFrequency(itemIds);

//        tableViewSalesReport.setItems(observableList);
    }
    public void handleLoadSalesClick(ActionEvent actionEvent) {
        loadSalesReportByDate();
    }

    public void loadExcessReport(Timestamp beginning_date){
        double threshhold;
        DatabaseHelper dbHelper = new DatabaseHelper();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Integer> orders = dbHelper.ordersByDate(beginning_date, now);

    }

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
}