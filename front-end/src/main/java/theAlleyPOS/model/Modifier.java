package theAlleyPOS.model;

import javafx.beans.property.SimpleIntegerProperty;

public class Modifier {
    private SimpleIntegerProperty id;
    private boolean pearls;
    private boolean snowVelvet;
    private double iceLevel;
    private double sweetnessLevel;

    public Modifier() {
        this.id = new SimpleIntegerProperty();
        this.pearls = false;
        this.snowVelvet = false;
        this.iceLevel = 0;
        this.sweetnessLevel = 0;
    }

    public Modifier(int id, boolean pearls, boolean snowVelvet, double iceLevel, double sweetnessLevel) {
        this.id = new SimpleIntegerProperty(id);
        this.pearls = pearls;
        this.snowVelvet = snowVelvet;
        this.iceLevel = iceLevel;
        this.sweetnessLevel = sweetnessLevel;
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
        return pearls;
    }

    public void setPearls(boolean pearls) {
        this.pearls = pearls;
    }

    public boolean isSnowVelvet() {
        return snowVelvet;
    }

    public void setSnowVelvet(boolean snowVelvet) {
        this.snowVelvet = snowVelvet;
    }

    public double getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(double iceLevel) {
        this.iceLevel = iceLevel;
    }

    public double getSweetnessLevel() {
        return sweetnessLevel;
    }

    public void setSweetnessLevel(double sweetnessLevel) {
        this.sweetnessLevel = sweetnessLevel;
    }
}
