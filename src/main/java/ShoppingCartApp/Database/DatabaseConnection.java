package ShoppingCartApp.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static DatabaseConnection instance = new DatabaseConnection();

    private String DB_URL = "jdbc:mariadb://localhost:3306/shopping_cart_localization";
    private String DB_USER = "app_user";
    private String DB_PASSWORD = "password";

    private DatabaseConnection(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB JDBC driver is missing from the classpath.", e);
        }
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }

    public Connection getDBConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to MariaDB using URL: " + DB_URL, e);
        }
        return conn;
    }
}
