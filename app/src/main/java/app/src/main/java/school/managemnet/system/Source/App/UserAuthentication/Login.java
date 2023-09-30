package app.src.main.java.school.managemnet.system.Source.App.UserAuthentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserUtils;

public class Login {

    private String Password;
    private int ID;

    public ConfigUserFromDatabaseResult LogIntoDatabase(QueryAPI query, Scanner sc) throws SQLException
    {
        ResultSet UserData = null;
        ConfigUserFromDatabaseResult result = null;
        try {
            GetUserContext(sc);
            UserData = query.Login_LogIntoDatabase(this.Password, this.ID);
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

    public void GetUserContext(Scanner scanner)
    {
        System.out.print("Enter User ID: ");
        this.ID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Password: ");
        this.Password = scanner.nextLine();
    }
}
