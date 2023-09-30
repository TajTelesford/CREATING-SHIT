package app.src.main.java.school.managemnet.system.Source.App.Application;
import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.DATABASECONNECTION;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.GraphicalUserInterfaceStartup;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.HeadlessStartUp;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class AppStart 
{
    static DATABASECONNECTION database;
    public static String input;
    QueryAPI ApplicationQuery = null;
    public static int[] Application_UserIDS = new int[User.MAX_NUMBER_OF_IDS];

    public AppStart() 
    {
        ConnectDatabase(
            "root", 
            "new_password", 
            "jdbc:mysql://root@localhost:3306/school_management_system"
        );        
        try{
            Application_UserIDS = ApplicationQuery.AppStart_FetchUserIDS();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        //set up gui for application or headless
    } 

    private void ConnectDatabase(String username, String password, String jdbcUrl)
    {
        database = new DATABASECONNECTION(username, password, jdbcUrl);
        ApplicationQuery = new QueryAPI(database);
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
            else if(Config.equals("--gui")) 
                GraphicalUserInterfaceStartup.Run();
            else{
                AdminView(sc);
            }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            ApplicationQuery.QueryShutdown();
    }

    public void AdminView(Scanner sc) throws SQLException
    {
        boolean loop = true;
        while(loop)
        {  
            System.out.print(
                    "========================================\n" + 
                    "1: Add User\n" +
                    "2: Show Users\n" +
                    "3: Delete Users\n" +
                    "4: Create Courses\n" +
                    "5: Add Student To Course\n" +
                    "======================================== Enter Choice: " 
            );

            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                //Pre: Get Create User
                    ApplicationQuery.Admin_AddUser(null);
                    break;
                case 2:
                    ApplicationQuery.Admin_ShowUsers();
                    break;
                case 3:
                //Pre: Get The User You Want To Delete
                    ApplicationQuery.Admin_DeleteUser(null);
                    break;
                case 4:
                    ApplicationQuery.Admin_MakeCourses(sc);
                    break;
                case 5:
                    ApplicationQuery.Admin_ConnectStudentToCourse(sc);
                    break;
            }
        }
    }
}

