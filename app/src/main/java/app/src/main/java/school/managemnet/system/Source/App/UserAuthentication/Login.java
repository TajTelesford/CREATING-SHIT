package app.src.main.java.school.managemnet.system.Source.App.UserAuthentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserUtils;

public class Login {

    private static String Password;
    private static int ID;

    public static ConfigUserFromDatabaseResult LogIntoDatabase(QueryAPI query, Scanner sc) throws SQLException
    {
        ResultSet UserData = null;
        ConfigUserFromDatabaseResult result = null;
        try {
            GetUserContext(sc);
            UserData = query.Login_LogIntoDatabase(Password, ID);
        } catch (Exception e) {
            System.out.println("Login Failed");
        } finally{
            result = new ConfigUserFromDatabaseResult(UserData);
        }

        ConfigUserUtils.WelcomeUserFunction(result);
        
        return result;
    }

    // private String HashPassword(String pass)
    // {
    //     return "NOT IMPLEMENTED";
    // }

    public static void GetUserContext(Scanner scanner)
    {
        System.out.print("Enter User ID: ");
        try {
            ID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Password: ");
            Password = scanner.nextLine();
        } catch (Exception e) {
            System.out.print("ID or Password Is Wrong, Try Again");
        }
        
         // Consume the newline character

        
    }
}
