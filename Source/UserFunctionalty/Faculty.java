package Source.UserFunctionalty;

import java.sql.SQLException;
import java.util.Scanner;

import Source.CourseComponenets.Assignment;
import Source.Database.Query;

public class Faculty extends User{

    public Faculty(String name, String email, String password)
    {
        super("teacher", name, email, password);
    }

    public Faculty(String name, String email, String password, int id)
    {
        super("teacher", name, email, password, id);
    }

    public void F_CreateAssignments(Query query, Scanner scanner) throws SQLException
    {
        //Get the file from the teacher
        String File;
        System.out.print("Enter Directory Of File: ");
        File = scanner.nextLine();
        //Get the name of the assignmnet from the user
        String assignment_name;
        System.out.print("Enter Directory Of File: ");
        assignment_name = scanner.nextLine();
        //Get Due Date "02-05-2023" -> Feburary, 05, 2023
        String assignment_duedate = GetDueDate(scanner);
        String assignment_description = scanner.nextLine();
        Assignment assignment = new Assignment(File, 
                                                assignment_name, 
                                                assignment_duedate, 
                                                assignment_description);
        query.Faculty_PostAssignment(assignment, this);
    }

    private String GetDueDate(Scanner sc)
    {
        String DueDate;
        do{
            System.out.print("Enter Due Date (MM-DD-YYYY): ");
            DueDate = sc.nextLine();
        }while(!ValiateDueDate(DueDate));

        return DueDate;
    }

    private boolean ValiateDueDate(String date)
    {
        int max_date_length = 10;
        if(date.length() > max_date_length || date.length() < max_date_length)
            return false;
        if(utilDueDateSetUp(date)) 
            return false;
        return true;
    } 

    private boolean utilDueDateSetUp(String date)
    {
        boolean delimeter = false;
        for(int i = 0; i < date.length(); i++)
        {
            if(date.charAt(i) == '-' && date.charAt(i + 3) == '-') delimeter = true;
            if(i == 0) //Check Month 
            {
                int month = Integer.parseInt(date, i, i + 1, i);
                if(month > 1 || month < 12) return false;
            }
            if(i == 3) //Check Day
            {
                int day = Integer.parseInt(date, i, i + 1, 1);
                if(day > 1 || day < 31) return false;
            }
        }
        return delimeter;
    }

    /*  
        - Create Assignments
            - Push Assignments to database
        - Grade Assignments
            - Pull Assignments info from students and grade it
            - Have a Incomplete, In-Progress, Complete, UnSubmitted
        - See student attendance
        - Submit grades to students
        - Email students
    */

}