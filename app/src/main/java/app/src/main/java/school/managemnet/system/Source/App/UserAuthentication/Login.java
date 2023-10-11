package app.src.main.java.school.managemnet.system.Source.App.UserAuthentication;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserUtils;

public class Login {

    private static String Password;
    private static int ID;
    private static int MAX_Login_Times = 3;
    public static ConfigUserFromDatabaseResult LogIntoDatabase(QueryAPI query, Scanner sc) throws SQLException
    {
        int count = 0;
        ConfigUserFromDatabaseResult result = null;
        do{
            try {
                GetUserContext(sc);
                result = query.Login_LogIntoDatabase(Password, ID);
                ConfigUserUtils.WelcomeUserFunction(result);
                if(result != null) return result;
            } catch (NullPointerException | SQLException | InputMismatchException e) {
                System.out.println("Login Failed");
            } 
        }while(count < MAX_Login_Times && GetUserContext(sc));
        return null;
    }


    // private String HashPassword(String pass)
    // {
    //     return "NOT IMPLEMENTED";
    // }

    public static boolean GetUserContext(Scanner scanner) throws InputMismatchException
    {
        System.out.print("Enter User ID: ");
        try {
            ID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Password: ");
            Password = scanner.nextLine();
        } catch (InputMismatchException e) {

            return false;
        }
        
        return true;
        

        
    }
}
