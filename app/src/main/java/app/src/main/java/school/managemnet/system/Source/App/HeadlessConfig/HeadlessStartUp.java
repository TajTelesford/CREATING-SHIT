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
    
    public static DataTypes HeadlessCustomType = new DataTypes(null, null, 0);

    public static void Run(QueryAPI query, Scanner UserInput) 
    {
        HeadlessCustomType.setDQuery(query);
        HeadlessCustomType.setScanner(UserInput);

        ConfigUserFromDatabaseResult USER = null;

        USER = ConfigUser(HeadlessCustomType);
        if(USER == null) return;
        
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
                        System.out.println("Bad Username And/Or Password");
                    }
                }
                else if(input.equals("login"))
                {
                    //Login
                    try{
                        user = Login.LogIntoDatabase(blob.getDQuery(), blob.getScanner());
                        break;
                    } catch ( SQLException e ){

                        System.out.print(e.getMessage());
                    }
                }
            }while(!ValidateInitialSignIn(input));
        return user;
    }

    private static boolean ValidateInitialSignIn(String input)
    {
        if(!input.equals("login") && !input.equals("sign up"))
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

        if(usr.user_type.equals("student"))
            OptionFactory.Student_FactoryPrintOptions();
        else if(usr.user_type.equals("teacher"))
            OptionFactory.Faculty_FactoryPrintOptions();

        do{
            option = usrInput.nextLine();
        }while(!ValidateOption(option, usr.user_type));

        if(HeadlessProgramLoop == false) return;

        OptionFactory.FactoryRun(Integer.parseInt(option), 
                                usr.user_type.equals("student") 
                                ? 
                                usr.getStudent() : 
                                usr.getFaculty() );
    }

    //Validates choice depending on user type
    private static boolean ValidateOption(String option, String user_type)
    {
        try{
            if(Integer.parseInt(option) > OptionFactory.FactoryMaxOptions || Integer.parseInt(option) < 1)
            {
                System.out.println("Not A Valid Option, Please Choose Again");
                return false;
            }

            else if(Integer.parseInt(option) == OptionFactory.FactoryMaxOptions)
            {
                QuitHeadlessLoop();
                return true;
            } 

            else if(user_type.equals("student") && Integer.parseInt(option) > OptionFactory.MaxStudentOptions)
            {
                System.out.println("Not Allowed To Use This Functionality");
                return false;
            }

            else if(user_type.equals("teacher") && Integer.parseInt(option) < OptionFactory.MinFacultyOptions)
            {
                System.out.println("Not Allowed To Use This Functionality");
                return false;
            }

            else if(Integer.parseInt(option) > OptionFactory.FactoryMaxOptions || Integer.parseInt(option) < 1)
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