package Application;
import Database.DATABASECONNECTION;

public class Application {
    static DATABASECONNECTION database;
    public static void main(String []args)
    {
        Application app = new Application();
        app.run();
    }

    public Application()
    {
        ConnectDatabase(
            "root", 
            "new_password", 
            "jdbc:mysql://root@localhost:3306/school_management_system"
        );
    }

    private void ConnectDatabase(String username, String password, String jdbcUrl)
    {
        database = new DATABASECONNECTION(username, password, jdbcUrl);
    }


    private void run()
    {

    }
}
