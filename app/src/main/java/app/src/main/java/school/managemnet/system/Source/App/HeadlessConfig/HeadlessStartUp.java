package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions.OptionFactory;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.Login;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.SignUp;


public class HeadlessStartUp {
    
    private static boolean HeadlessProgramLoop = true;
    
    static DataTypes HeadlessCustomType = new DataTypes(null, null, 0);

    public static void Run(Query query, Scanner UserInput) 
    {
        HeadlessCustomType.setDQuery(query);
        HeadlessCustomType.setScanner(UserInput);

        ConfigUserFromDatabaseResult USER = null;
        System.out.println("Login or SignUp?: ");

        String userType = UserInput.nextLine().toLowerCase(); // Read and convert to lowercase

       if (userType.equals("signup")) {
            try {
                SignUp userSignUp = new SignUp();
                userSignUp.SignUpUser(query, UserInput);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Login
        USER = login(query, UserInput);

        //Program Loop
        try{
            HeadlessStartUp_Program_Menu(UserInput, USER, query);
        } catch (Exception e) {    
            e.printStackTrace();
        }

        UserInput.close();
    }

    private static ConfigUserFromDatabaseResult login(Query query, Scanner scanner)
    {
        ConfigUserFromDatabaseResult result = null;
        try {
            Login userLogin = new Login();
            result = userLogin.LogIntoDatabase(query, scanner);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Failed Here in Headless login");
        }
        return result;
    }

    private static void HeadlessStartUp_Program_Menu(Scanner UserInput, ConfigUserFromDatabaseResult user, Query query) 
    throws SQLException
    {
        while(HeadlessProgramLoop)
        {
            UserChoiceOptions(user, UserInput);
        }
    }

    private static void UserChoiceOptions(ConfigUserFromDatabaseResult usr, Scanner usrInput)
    {
        
        String option = null;
        switch(usr.user_type)
        {
            case "student":
                OptionFactory.Student_FactoryPrintOptions();
                do
                {
                    option = usrInput.nextLine();
                }while(!ValidateOption(Integer.parseInt(option)));

                if(Integer.parseInt(option) == OptionFactory.FactoryMaxOptions)
                {
                    QuitHeadlessLoop();
                    return;
                } 
                OptionFactory.FactoryRun(Integer.parseInt(option), usr.getStudent(), HeadlessCustomType);
                break;
                
            case "teacher":
                OptionFactory.Faculty_FactoryPrintOptions();
                do{
                    option = usrInput.nextLine();
                }while(!ValidateOption(Integer.parseInt(option)));

                if(Integer.parseInt(option) == OptionFactory.FactoryMaxOptions)
                {
                    QuitHeadlessLoop();
                    return;
                }
                OptionFactory.FactoryRun(Integer.parseInt(option), usr.getFaculty(), HeadlessCustomType);
                break;
            default:
                System.out.println("Something went wrong with ConfigUserFromDatabase");
                
        }
    }

    //Validates choice depending on user type
    private static boolean ValidateOption(int option)
    {
        try{
            if(option > OptionFactory.FactoryMaxOptions || option < 1)
            {
                System.out.println("Not A Valid Option, Please Choose Again");
                return false;
            }
        } catch (NumberFormatException e)
        {
            System.out.println("Not A Valid Option, Please Choose Again");
            return false;
        }
        return true;
    }

    private static void QuitHeadlessLoop()
    {
        HeadlessProgramLoop = false;
    }
}