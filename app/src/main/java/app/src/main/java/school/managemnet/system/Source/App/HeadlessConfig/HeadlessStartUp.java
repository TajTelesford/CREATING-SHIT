package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.Login;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.SignUp;
import app.src.main.java.school.managemnet.system.Source.App.UserOptions.OptionFactory;


public class HeadlessStartUp {
    
    private static boolean HeadlessProgramLoop = true;
    
    static DataTypes HeadlessCustomType = new DataTypes(null, null, 0);

    public static void Run(QueryAPI query, Scanner UserInput) 
    {
        HeadlessCustomType.setDQuery(query);
        HeadlessCustomType.setScanner(UserInput);

        ConfigUserFromDatabaseResult USER = null;

        USER = ConfigUser(HeadlessCustomType);
        
        //Program Loop
        try{
            HeadlessStartUp_Program_Menu(UserInput, USER, query);
        } catch (Exception e) {    
            e.printStackTrace();
        }
        UserInput.close();
    }
    
    private static ConfigUserFromDatabaseResult ConfigUser(DataTypes blob)
    {
        String input = null;
        ConfigUserFromDatabaseResult user = null;
        do{
            System.out.println("Login or SignUp?: ");
            input = blob.getScanner().nextLine().toLowerCase();
            if (input.equals("signup")) {
                    try {
                        SignUp userSignUp = new SignUp();
                        userSignUp.SignUpUser(blob.getDQuery(), blob.getScanner());
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if(input.equals("login"))
                {
                    //Login
                    try{
                        user = Login.LogIntoDatabase(blob.getDQuery(), blob.getScanner());
                        break;
                    } catch ( SQLException e ){
                        System.out.print("{ Couldn't Login, Try Again }\n ->");
                    }
                }
            }while(!ValidateInitialSignIn(input));
        return user;
    }

    private static boolean ValidateInitialSignIn(String input)
    {
        if(!input.equals("login") || !input.equals("sign up"))
        {
            System.out.println("Not A Valid Choose, Rethink your life");
            return false;
        }
        return true;
    }

    private static void HeadlessStartUp_Program_Menu(Scanner UserInput, ConfigUserFromDatabaseResult user, QueryAPI query) 
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
                }while(!ValidateOption(option));

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
                }while(!ValidateOption(option));

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
    private static boolean ValidateOption(String option)
    {
        try{
            if(Integer.parseInt(option) > OptionFactory.FactoryMaxOptions || Integer.parseInt(option) < 1)
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