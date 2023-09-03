package UserAuthentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Source.Database.Query;

public class Login {

    private String Password;
    private int ID;

    public void LogIntoDatabase(Query query) throws SQLException
    {
        ResultSet UserData = null;
        try {
            UserData = query.Login_LogIntoDatabase(Password, ID);
        } catch (Exception e) {
            System.out.println("Login Failed");
        }

        while( UserData.next() )
        {
            String UserType = UserData.getString("user_type");
            String name = UserData.getString("name");
            System.out.println("Welcome , " + name + "! User Type: " + UserType.toUpperCase());
            
        }
        query.CloseConnection();
    }

    // private String HashPassword(String pass)
    // {
    //     return "NOT IMPLEMENTED";
    // }

    public void GetUserContext(Scanner scanner)
    {
        System.out.print("Enter User ID: ");
        ID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Password: ");
        Password = scanner.nextLine();
    }
}
