package theAlleyPOS.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public class Order {
    private SimpleIntegerProperty id;
    private SimpleStringProperty customerName;
    private SimpleObjectProperty<Date> orderDate;
    private SimpleDoubleProperty totalCost;

    public Order() {
        this.id = new SimpleIntegerProperty();
        this.customerName = new SimpleStringProperty();
        this.orderDate = new SimpleObjectProperty<>();
        this.totalCost = new SimpleDoubleProperty();
    }

    public Order(int id, String customerName, Date orderDate, double totalCost) {
        this.id = new SimpleIntegerProperty(id);
        this.customerName = new SimpleStringProperty(customerName);
        this.orderDate = new SimpleObjectProperty<>(orderDate);
        this.totalCost = new SimpleDoubleProperty(totalCost);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public Date getOrderDate() {
        return orderDate.get();
    }

    public SimpleObjectProperty<Date> orderDateProperty() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate.set(orderDate);
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public SimpleDoubleProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost.set(totalCost);
    }
}
