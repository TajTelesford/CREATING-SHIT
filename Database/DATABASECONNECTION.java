package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATABASECONNECTION {
    // private String jdbcUrl;
    // private String user;
    // private String password;
    Connection connection;
    public DATABASECONNECTION(String user, String password, String jdbcUrl)
    {
        try {
            // Step 1: Load and register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connected to the database!");

            CloseConnection();
            System.out.println("DONE");
        } 
        catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
            CloseConnection();
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Connection getConnection() { return connection; }
    
    public void CloseConnection()
    {
        try {
            connection.close();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
