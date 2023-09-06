package UserAuthentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Source.Database.Query;
import Source.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import Source.HeadlessConfig.ConfigUser.ConfigUserUtils;



//TODO:WHEN YOU WAKE UP INIT A STUDENT OR A FACULTY OBJECT TO BE USED
//DO NOT RETURN A RESULT SET
public class Login {

    private String Password;
    private int ID;

    public ConfigUserFromDatabaseResult LogIntoDatabase(Query query, Scanner sc) throws SQLException
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
