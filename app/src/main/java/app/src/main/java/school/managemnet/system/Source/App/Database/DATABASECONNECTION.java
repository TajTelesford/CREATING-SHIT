package app.src.main.java.school.managemnet.system.Source.App.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TODO: Create abstract class DATABASECONNECTION

public class DATABASECONNECTION {
    private String JdbcUrl = null;
    private String Username = null;
    private String Password = null;
    private Connection connection = null;

    public DATABASECONNECTION(String user, String password, String jdbcUrl)
    {
        JdbcUrl = jdbcUrl;
        Username = user;
        Password = password;
    }

    public Connection GetConnection() { return connection; }

    public void SecureConnection()
    {
        try {
            // Step 1: Load and register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to the database
            connection = DriverManager.getConnection(JdbcUrl, Username, Password);
            System.out.println("Connected to the database!");
            if(connection == null) System.out.println("Connection is null in Secure");
        } 
        catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
            CloseConnection();
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

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
