package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.Login;
import app.src.main.java.school.managemnet.system.Source.App.UserAuthentication.SignUp;


public class HeadlessStartUp {
    
    private static boolean HeadlessProgramLoop = true;
    private static Scanner HeadlessUserInput = null;

    public enum OptionsForUsers {
        S_ViewGpa(0), 
        S_TakeAssignments(1), S_SubmitAssignment(2), 
        S_SubmitAttendance(3), S_SeeGrades(4), S_EmailFaculty(5),
        F_CreateAssignments(6), F_ShowCourses(7) ,
        F_OpenAssignments(8) ,F_GradeAssignments(9), 
        F_SeeStudentAttendance(10),
        F_SubmitStudentGrades(11), F_EmailStudents(12),
        APP_Quit(13);

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
                    usr.getStudent().getUserName() + " " + usr.getStudent().getUserID() + "\n" +
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
                    usr.getFaculty().getUserName() + " " + usr.getFaculty().getUserID() + "\n" +
                    "1: Create Assignments\n" +
                    "2: Show Courses\n" +
                    "3: Open An Assignment\n" +
                    "4: Grade Assignments\n" +
                    "5: See Student Attendance\n" +
                    "6: Submit Grades to students\n" +
                    "7: Email Students\n" +
                    "8: Quit Application\n" +
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
        if(choice == 6) choice = 13; // User wants to quit
        else  choice = choice - 1; //Works better for enum
        switch(OptionsForUsers.fromValue(choice))
        {
            case S_ViewGpa:
                usr.getStudent().GetGpa();
                break;
            case S_SeeGrades:
                //implement a function to see grades and their courses
                break;
            case S_TakeAssignments:
                usr.getStudent().TakeAssignment();
                break;
            case S_SubmitAssignment:
                usr.getStudent().SubmitAssignmnet();
                break;
            case S_SubmitAttendance:
                usr.getStudent().SubmitAttendance();
                break;
            case S_EmailFaculty:
                usr.getStudent().EmailTeacher();
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
        // choice = 7 => 7 + 5 => 12
        
        int offset = 5;
        switch(OptionsForUsers.fromValue(choice + offset))
        {
            case F_CreateAssignments:
                usr.getFaculty().CreateAssignment(query, HeadlessUserInput);
                break;
            case F_ShowCourses:
                usr.getFaculty().ShowCourses(query);
                break;
            case F_OpenAssignments:
                usr.getFaculty().OpenAssignment(query, HeadlessUserInput);
                break;
            case F_GradeAssignments:
                usr.getFaculty().GradeAssignment();
                break;
            case F_SubmitStudentGrades:
                break;
            case F_SeeStudentAttendance:
                usr.getFaculty().ViewStudentAttendance();
                break;
            case F_EmailStudents:
                usr.getFaculty().EmailStudent();
                //or
                usr.getFaculty().EmailStudent();
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