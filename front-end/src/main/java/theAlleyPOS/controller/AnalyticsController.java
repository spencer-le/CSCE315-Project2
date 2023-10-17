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
import java.util.ArrayList;
import java.util.List;

public class AnalyticsController {
    public TextField minimumAmountField;
    public TableColumn columnItemId;
    public TableColumn columnItemName;
    public TableColumn columnItemAmount;
    public Button btnLoadItems;
    @FXML
    private Button homeButton1;
    @FXML
    private Button homeButton2;
    @FXML
    private Button homeButton3;
    @FXML
    private Button homeButton4;
    @FXML
    private Button homeButton5;
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
    private TableView tableViewSeasonal;
    @FXML
    private TableView tableViewSalesTogether;
    @FXML
    private TableView tableViewRestockReport;

    @FXML
    public void initialize() {
        columnItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        columnItemAmount.setCellValueFactory(new PropertyValueFactory<>("inventoryCount"));
    }

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

    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        loadManagerTimeClockScreen(actionEvent);
    }

    @FXML
    public void handleLoadItemsClick(ActionEvent actionEvent) {
        loadRestockItems();
    }
}

//    public void handleExcessReport(int begin_date){
//        //takes in the beginning date until the current day (day 365)
//        theAlleyPOS.DatabaseHelper dbHelper = new theAlleyPOS.DatabaseHelper();
//        dbHelper.fetchItemsBelowInventoryCount(/*begin*.10*/); //needs beginning inventory
//
//        /*
//        *** Need to add items ordered to dataset/inventory count on a given day
//        * for each item in the database
//        * find out how many were sold
//        * beginning inventory = (current_inventory_count + COUNT(how many were sold))
//        * if ((how many sold / beginning inventory) < .10 ){
//        *   add to report
//        * }
//        *  */
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT item_name FROM items ORDER BY id";
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            sql = "SELECT item_name FROM items ORDER BY id";
//            while (rs.next()) {
//                pstmt = conn.prepareStatement(sql)
//                //items.add(new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }




























//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT * FROM items ORDER BY id";
//
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                items.add(new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//}
