package theAlleyPOS.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Sebastian Oberg, Spencer Le
 */
public class Modifier implements Orderable {
    private SimpleIntegerProperty id;
    private SimpleBooleanProperty pearls;
    private SimpleBooleanProperty snowVelvet;
    private SimpleDoubleProperty iceLevel;
    private SimpleDoubleProperty sweetnessLevel;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inventoryCount;
    private SimpleStringProperty modifierName;

    /**
     * Default Constructor
     */
    public Modifier() {
        this.id = new SimpleIntegerProperty();
        this.pearls = new SimpleBooleanProperty(false);
        this.snowVelvet = new SimpleBooleanProperty(false);
        this.iceLevel = new SimpleDoubleProperty(0);
        this.sweetnessLevel = new SimpleDoubleProperty(0);
        this.price = new SimpleDoubleProperty();
        this.inventoryCount = new SimpleIntegerProperty();
    }

    /**
     * Parameterized constructor
     * @param id
     * @param modifierName
     * @param price
     * @param inventoryCount
     */
    public Modifier(int id, String modifierName, double price, int inventoryCount) {
        this.id = new SimpleIntegerProperty(id);
        this.modifierName = new SimpleStringProperty(modifierName);
        this.price = new SimpleDoubleProperty(price);
        this.inventoryCount = new SimpleIntegerProperty(inventoryCount);
    }

    /**
     * The rest of the following are getters and setters for each modifier
     * @return
     */
    public String getModifierName() {
        return modifierName.get();
    }

    public SimpleStringProperty modifierNameProperty() {
        return modifierName;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public boolean isPearls() {
        return pearls.get();
    }

    public void setPearls(boolean pearls) {
        this.pearls.set(pearls);
    }

    public SimpleBooleanProperty pearlsProperty() {
        return pearls;
    }

    public boolean isSnowVelvet() {
        return snowVelvet.get();
    }

    public void setSnowVelvet(boolean snowVelvet) {
        this.snowVelvet.set(snowVelvet);
    }

    public SimpleBooleanProperty snowVelvetProperty() {
        return snowVelvet;
    }

    public double getIceLevel() {
        return iceLevel.get();
    }

    public void setIceLevel(double iceLevel) {
        this.iceLevel.set(iceLevel);
    }

    public SimpleDoubleProperty iceLevelProperty() {
        return iceLevel;
    }

    public double getSweetnessLevel() {
        return sweetnessLevel.get();
    }

    public void setSweetnessLevel(double sweetnessLevel) {
        this.sweetnessLevel.set(sweetnessLevel);
    }

    public SimpleDoubleProperty sweetnessLevelProperty() {
        return sweetnessLevel;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public int getInventoryCount() {
        return inventoryCount.get();
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount.set(inventoryCount);
    }

    public SimpleIntegerProperty inventoryCountProperty() {
        return inventoryCount;
    }

    @Override
    public String getName() {
        return getModifierName();
    }

    @Override
    public Double getPrice() {
        return price.get();
    }
}