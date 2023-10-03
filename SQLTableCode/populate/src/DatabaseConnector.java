import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Reference: https://www.postgresqltutorial.com/postgresql-jdbc/query/
public class DatabaseConnector {
    private Connection connection;

    public void connect() {
        try {
            String url = "jdbc:postgresql://localhost:5432/_database_name";
            String username = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}