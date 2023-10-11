package theAlleyPOS.model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleIntegerProperty id;
    private SimpleStringProperty itemName;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inventoryCount;

    public Item() {
        this.id = new SimpleIntegerProperty();
        this.itemName = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inventoryCount = new SimpleIntegerProperty();
    }

    public Item(int id, String itemName, double price, int inventoryCount) {
        this.id = new SimpleIntegerProperty(id);
        this.itemName = new SimpleStringProperty(itemName);
        this.price = new SimpleDoubleProperty(price);
        this.inventoryCount = new SimpleIntegerProperty(inventoryCount);
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

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getInventoryCount() {
        return inventoryCount.get();
    }

    public SimpleIntegerProperty inventoryCountProperty() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount.set(inventoryCount);
    }
}
