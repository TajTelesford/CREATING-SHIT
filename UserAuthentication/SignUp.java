package UserAuthentication;

import java.sql.SQLException;
import java.util.Scanner;

import Source.Database.Query;
import Source.UserFunctionalty.User;

public class SignUp {
    public void SignUpUser(Query query, Scanner scanner) throws SQLException
    {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter User Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String Password = scanner.nextLine();

        System.out.println("Student or Faculty?: ");
        String user_type = scanner.nextLine().toLowerCase();
        
        User CreatedUser = new User(user_type, name, email, Password);
        try {
            query.Admin_AddUser(CreatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        

        System.out.println(
            "WELCOME { " + name + " } Your ID is: { " + CreatedUser.getUserID() + " }"
        );
    }
}
