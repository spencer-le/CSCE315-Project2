package theAlleyPOS;
import javafx.beans.property.SimpleIntegerProperty;
import theAlleyPOS.model.Item;
import theAlleyPOS.model.Order;
import theAlleyPOS.model.Modifier;
import theAlleyPOS.model.UserRole;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db";
    private static final String USER = "csce315_909_roshantayab";
    private static final String PASS = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public UserRole validateUser(String ID) {
        if (isIDValid(ID, "Managers")) {
            return UserRole.MANAGER;
        } else if (isIDValid(ID, "Cashiers")) {
            return UserRole.EMPLOYEE;
        } else {
            return UserRole.NONE;
        }
    }

    private boolean isIDValid(String ID, String tableName) {
        String sql = "SELECT password FROM " + tableName + " WHERE password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int password = Integer.parseInt(ID);
            pstmt.setInt(1, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> fetchItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> fetchOtherItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE id > 19";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public void addItem(Item item) {
        String sql = "INSERT INTO items (id, item_name, price, inventory_count) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getItemName());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setInt(4, item.getInventoryCount());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItemInDatabase(Item item) {
        String sql = "UPDATE items SET item_name = ?, price = ?, inventory_count = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getItemName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getInventoryCount());
            pstmt.setInt(4, item.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemByName(String itemName) {
        Item item = null;
        String sql = "SELECT * FROM items WHERE item_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void decrementItemInventory(String itemName) {
        String sql = "UPDATE items SET inventory_count = inventory_count - 1 WHERE item_name = ? AND inventory_count > 0";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> fetchItemsBelowInventoryCount(int threshold) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE inventory_count < ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, threshold);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(rs.getInt("id"), rs.getString("item_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public SimpleIntegerProperty getNewOrderID() {
        String sql = "SELECT COUNT(*) FROM orders";
        SimpleIntegerProperty newID = new SimpleIntegerProperty(); // Initialize newID
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int count = rs.getInt(1);
                    newID.set(count + 1); // Set the value of newID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public List<Modifier> fetchModifiers() {
        List<Modifier> modifiers = new ArrayList<>();
        String sql = "SELECT * FROM MODIFIERS";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modifiers.add(new Modifier(rs.getInt("id"), rs.getString("modifier_name"), rs.getDouble("price"), rs.getInt("inventory_count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modifiers;
    }

    public void decrementModifierInventory(String modifierName) {
        String sql = "UPDATE MODIFIERS SET inventory_count = inventory_count - 1 WHERE modifier_name = ? AND inventory_count > 0";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, modifierName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Modifier getModifierByName(String modifierName) {
        Modifier modifier = null;
        String sql = "SELECT * FROM MODIFIERS WHERE modifier_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, modifierName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    modifier = new Modifier(rs.getInt("id"), rs.getString("modifier_name"), rs.getDouble("price"), rs.getInt("inventory_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modifier;
    }

    public void addOrder(Order order) {
//        INSERT INTO orders (id, customer_name, order_date, total_cost) VALUES (1, 'Spencer', '2023-10-16 14:30:00', 10.0);
        String sql = "INSERT INTO orders (id, customer_name, order_date, total_cost) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(order.getOrderDate()));
            pstmt.setDouble(4, order.getTotalCost());
//            pstmt.setInt(1, 2); // ID
//            pstmt.setString(2, "Le"); // Customer Name
//            pstmt.setString(3, "2023-10-16 14:30:00"); // Order Date in the specified format
//            pstmt.setDouble(4, 11.0); // Total Cost

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
Managers:
       id |       name       | password
       ---|------------------|----------
        0 | Grant Shields    | 9234
        1 | Blake Dugan      | 9345
        2 | Roshan Tayab     | 9456
        3 | Sebastian Oberg  | 9567
        4 | Spencer Le       | 9678

Employees:
       id |       name       | password
       ---|------------------|----------
        0 | Grant Shields    | 1234
        1 | Blake Dugan      | 1345
        2 | Roshan Tayab     | 1456
        3 | Sebastian Oberg  | 1567
        4 | Spencer Le       | 1678
*/