public class Order {
    int OrderNumber;
    String CustomerName;
    String DateTime;
    float TotalCost;

    public Order(int OrderNumber, String CustomerName, String DateTime, float TotalCost) {
        this.OrderNumber = OrderNumber;
        this.CustomerName = CustomerName;
        this.DateTime = DateTime;
        this.TotalCost = TotalCost;
    }
}
