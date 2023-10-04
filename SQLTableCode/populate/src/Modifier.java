public class Modifier {
    int ModifierID;
    String ModifierName;
    float Price;
    int InventoryCount;

    public Modifier(int ModifierID, String ModifierName, float Price, int InventoryCount) {
        this.ModifierID = ModifierID;
        this.ModifierName = ModifierName;
        this.Price = Price;
        this.InventoryCount = InventoryCount;
    }
}
