package Source.Application;
import java.util.Scanner;

import Source.Database.DATABASECONNECTION;
import Source.Database.Query;
import Source.GraphicalUserInterfaceConfig.GraphicalUserInterfaceStartup;
import Source.HeadlessConfig.HeadlessStartUp;
import Source.UserFunctionalty.User;

public class Application {
    static DATABASECONNECTION database;
    public static String input;
    Query ApplicationQuery = null;
    static int[] Application_UserIDS = new int[User.MAX_NUMBER_OF_IDS];

    public Application()
    {
        ConnectDatabase(
            "root", 
            "new_password", 
            "jdbc:mysql://root@localhost:3306/school_management_system"
        );

        //set up a global list of user id's
        
        //set up gui for application or headless
    } 

    private void ConnectDatabase(String username, String password, String jdbcUrl)
    {
        database = new DATABASECONNECTION(username, password, jdbcUrl);
        ApplicationQuery = new Query(database);
    }

    public void run() 
    {
        try{
            Scanner sc = new Scanner(System.in) ;
            String Config = sc.nextLine();

            //high level application loop
            if(Config.equals("--headless")) 
            {
                HeadlessStartUp.Run(ApplicationQuery, sc);
            }
            if(Config.equals("--gui")) 
                GraphicalUserInterfaceStartup.Run();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            ApplicationQuery.QueryShutdown();
        // try {
        //     database.SecureConnection();
        //     Query q = new Query(database);
        //     q.Admin_AddUser(u);
        //     database.CloseConnection();
        // } catch (SQLException e) {
        //    
        //     e.printStackTrace();
        //}
    }
}
