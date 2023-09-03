package Source.Headless;

import java.sql.SQLException;
import java.util.Scanner;


import Source.Database.Query;
import UserAuthentication.Login;
import UserAuthentication.SignUp;

public class HeadlessStartUp {
    
    public static void Run(Query query, Scanner UserInput) 
    {
        System.out.println("Login or SignUp?: ");

        String userType = UserInput.nextLine().toLowerCase(); // Read and convert to lowercase
        // No need to close the Scanner here

        if (userType.equals("login")) {
            Login(query, UserInput);
        } else if (userType.equals("signup")) {
            try {
                SignUp userSignUp = new SignUp();
                userSignUp.SignUpUser(query, UserInput);

                //Add a login feature here
                Login(query, UserInput);
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
        }

       UserInput.close();
    }

    public static void Login(Query query, Scanner scanner)
    {
        try {
            Login userLogin = new Login();
            userLogin.GetUserContext(scanner);
            userLogin.LogIntoDatabase(query);

            //main program loop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void HeadlessStartUp_Program_Menu(Scanner UserInput)
    {
        
    }


}
