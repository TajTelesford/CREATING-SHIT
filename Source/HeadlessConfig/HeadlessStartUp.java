package Source.HeadlessConfig;

import java.sql.SQLException;
import java.util.Scanner;

import Source.Database.Query;
import Source.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import UserAuthentication.Login;
import UserAuthentication.SignUp;


public class HeadlessStartUp {
    
    private static boolean HeadlessProgramLoop = true;
    private static Scanner HeadlessUserInput = null;

    public enum OptionsForUsers {
        S_ViewGpa(0), 
        S_TakeAssignments(1), S_SubmitAssignment(2), 
        S_SubmitAttendacne(3), S_SeeGrades(4), S_EmailFaculty(5),
        F_CreateAssignments(6), F_GradeAssignments(7), 
        F_SeeStudentAttendance(8),
        F_SubmitStudentGrades(9), F_EmailStudents(10),
        APP_Quit(11);

        private final int val;

        OptionsForUsers(int value)
        {
            this.val = value;
        }

        public int getValue()
        {
            return val;
        }

        public static OptionsForUsers fromValue(int val)
        {
            for (OptionsForUsers option : OptionsForUsers.values())
            {
                if(option.getValue() == val) 
                    return option;
            }
            throw new IllegalArgumentException ("Value Not In Options");
        }
    }
    
    public static void Run(Query query, Scanner UserInput) 
    {
        ConfigUserFromDatabaseResult USER = null;
        System.out.println("Login or SignUp?: ");

        HeadlessUserInput = UserInput;

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
            int Choice = UserChoiceOptions(user, UserInput);
            if (Choice == -1) 
                System.out.println("ERROR");
            if(user.user_type.equals("student"))
                StudentChoiceFunction(Choice, user);
            if(user.user_type.equals("teacher"))
                FacultyChoiceFunction(Choice, user, query);
        }
    }

    private static int UserChoiceOptions(ConfigUserFromDatabaseResult usr, Scanner usrInput)
    {
        String option = null;
        switch(usr.user_type)
        {
            case "student":
                System.out.print(
                    "========================================\n" + 
                    usr.getStudentFunctionality().getUserName() + " " + usr.getStudentFunctionality().getUserID() + "\n" +
                    "1: View Gpa\n" +
                    "2: Take Assignments\n" +
                    "3: Submit Attendence\n" +
                    "4: See Grades\n" +
                    "5: Email Teachers\n" +
                    "6: Quit Application\n" +
                    "======================================== Enter Choice: " 
                    );

                    do{
                        option = usrInput.nextLine();
                    }while(!ValidateOption(Integer.parseInt(option), usr.user_type));
                return Integer.parseInt(option);
                
            case "teacher":
                System.out.print(
                    "========================================\n" + 
                    usr.getFacultyFunctionality().getUserName() + " " + usr.getFacultyFunctionality().getUserID() + "\n" +
                    "1: Create Assignments\n" +
                    "2: Grade Assignments\n" +
                    "3: See Student Attendance\n" +
                    "4: Submit Grades to students\n" +
                    "5: Email Students\n" +
                    "6: Quit Application\n" +
                    "======================================== Enter Choice: " 
                    );

                    do{
                        option = usrInput.nextLine();
                    }while(!ValidateOption(Integer.parseInt(option), usr.user_type));
                return Integer.parseInt(option);

            default:
                System.out.println("Something went wrong with ConfigUserFromDatabase");
                return -1;
                
        }
        
    }

    private static void StudentChoiceFunction(int choice, ConfigUserFromDatabaseResult usr)
    {
        if(choice == 6) choice = 11; // User wants to quit
        else  choice = choice - 1; //Works better for enum
        switch(OptionsForUsers.fromValue(choice))
        {
            case S_ViewGpa:
                usr.getStudentFunctionality().Gpa();
                break;
            case S_SeeGrades:
                break;
            case S_TakeAssignments:
                break;
            case S_SubmitAssignment:
                break;
            case S_SubmitAttendacne:
                break;
            case S_EmailFaculty:
                break;
            case APP_Quit:
                QuitHeadlessLoop();
                break;
            default:
                break;
        }
        
    }

    private static void FacultyChoiceFunction(int choice, ConfigUserFromDatabaseResult usr, Query query) 
    throws SQLException
    {
         //Works better with enums
        // choice = 6 => 6 + 5 => 11
        System.out.println("Ted: " + choice);
        switch(OptionsForUsers.fromValue(choice + 5))
        {
            case F_CreateAssignments:
                usr.getFacultyFunctionality().F_CreateAssignments(query, HeadlessUserInput);
                break;
            case F_GradeAssignments:
                break;
            case F_SubmitStudentGrades:
                break;
            case F_SeeStudentAttendance:
                break;
            case F_EmailStudents:
                break;
            case APP_Quit:
                QuitHeadlessLoop();
                break;
            default:
                break;
        }

    }

    //Validates choice depending on user type
    private static boolean ValidateOption(int option, String user_Type)
    {
        int MaxOptions = 11;
        try{
            
            if(option > MaxOptions || option < 1)
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