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
import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastian Oberg, Spencer Le, Roshan Tayab
 */
public class DatabaseHelper {
    /**
     * Lines 28 through 34 both create variables for, and run the getConnection function, which attempts to connect to
     * the database and throws an exception if it can't
     */
    private static final String DB_URL = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_09g_db";
    private static final String USER = "csce315_909_roshantayab";
    private static final String PASS = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * The validateUser function determines whether the input ID belongs to a cashier, manager, or neither.
     * @param ID
     * @return UserRole
     */
    public UserRole validateUser(String ID) {
        if (isIDValid(ID, "Managers")) {
            return UserRole.MANAGER;
        } else if (isIDValid(ID, "Cashiers")) {
            return UserRole.EMPLOYEE;
        } else {
            return UserRole.NONE;
        }
    }

    /**
     * The isIdValid function determines whether the input ID matches one in the database.
     * @param ID
     * @param tableName
     * @return true or false
     */
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

    /**
     * The ordersByDate function reads in orders from the database between given dates, and returns them
     * @param beginTimestamp
     * @param endTimestamp
     * @return orderIds
     */
    public List<Integer> ordersByDate(Timestamp beginTimestamp, Timestamp endTimestamp) {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT id FROM Orders WHERE order_date BETWEEN ? AND ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, beginTimestamp);
            pstmt.setTimestamp(2, endTimestamp);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderIds.add(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderIds;
    }

    /**
     * The getItemIdsByOrderIds function retrieves and returns a list of items from a junction table given the order ID.
     * @param orderIds
     * @return itemIds
     */
    public List<Integer> getItemIdsByOrderIds(List<Integer> orderIds) {
        List<Integer> itemIds = new ArrayList<>();
        String sql = "SELECT item_id FROM ordered_items WHERE order_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Integer orderId : orderIds) {
                pstmt.setInt(1, orderId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    itemIds.add(rs.getInt("item_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemIds;
    }

    /**
     * The calculateItemFrequency function creates and returns an item frequency map, determined by the given item IDs.
     * @param itemIds
     * @return frequencyMap
     */
    public Map<Integer, Integer> calculateItemFrequency(List<Integer> itemIds) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (Integer itemId : itemIds) {
            frequencyMap.put(itemId, frequencyMap.getOrDefault(itemId, 0) + 1);
        }

        // Printing the frequency of each item
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("Item ID: " + entry.getKey() + ", Frequency: " + entry.getValue());
        }

        return frequencyMap;
    }


    /**
     * The fetchItems function collects all items in the database into an array for use
     * @return items
     */
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

    /**
     * The fetchOtherItems collects all new items that have been added (beyond the 20 original ones)
     * @return items
     */
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

    /**
     * The addItem function takes in an item to add to the database, and attempts to do so using SQL.
     * @param item
     */
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

    /**
     * The deleteItem function takes in an item to delete from the database, and attempts to do so using SQL.
     * @param id
     */
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

    /**
     * The updateItemInDatabase function takes in an item to update in the database and attempts to do so using SQL.
     * @param item
     */
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

    /**
     * The getItemByName function attempts to find the info of an item from the database given its name, using SQL.
     * @param itemName
     * @return item
     */
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

    /**
     * The decrementItemInventory function is called whenever an order is finalized, and it decrements the item listed,
     * unless it is already at 0, using SQL.
     * @param itemName
     */
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

    /**
     * The fetchItemsBelowInventoryCount function returns all items from the database which are below a given count, using
     * SQL.
     * @param threshold
     * @return items
     */
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

    /**
     * The getNewOrderID function is used to set a unique order ID for the given customer by incrementing the last order
     *      * by one, using SQL.
     * @return newID
     */
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

    /**
     * The fetchModifiers function returns all the available modifiers from the database using SQL.
     * @return modifiers
     */
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

    /**
     * The decrementModifierInventory function is called whenever an order is finalized, and it decrements the modifier
     * listed, unless it is already at 0, using SQL.
     * @param modifierName
     */
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

    /**
     * The getModifierByName function attempts to find the info of a modifier from the database given its name, using SQL.
     * @param modifierName
     * @return modifier
     */
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

    /**
     * The addOrder function attempts to add a given order and all its variables into the database using SQL.
     * @param order
     */
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

    /**
     * The addToOrderedItems function takes in the order and item IDs to link them in the junction table ordered_items
     * @param order_id
     * @param item_id
     */
    public void addToOrderedItems(int order_id, int item_id) {
        String sql = "INSERT INTO ordered_items (order_id, item_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order_id);
            pstmt.setInt(2, item_id);
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