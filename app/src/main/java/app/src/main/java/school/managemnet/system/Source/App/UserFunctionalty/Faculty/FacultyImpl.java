package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.Assignment;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.AssignmentView;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.course;
import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;


//TODO: Create Functionality for teachers to have multiple courses
/*
 * Make helper functions to get correct answers from database, 
 *      Submitting correct answers to database
 */

public class FacultyImpl extends User implements FacultyInterface {

    private course TeacherCourses;

    public FacultyImpl(String name, String email, String password)
    {
        super("teacher", name, email, password);
    }

    public FacultyImpl(String name, String email, String password, int id)
    {
        super("teacher", name, email, password, id);
    }
    
    @Override
    public void CreateAssignment(Query query, Scanner scanner) throws SQLException {
        //Get the file from the teacher
        String File;
        do{
            System.out.print("Enter Directory Of File: ");
            File = scanner.nextLine();
        }while(!new java.io.File(File).exists());

        //Get the name of the assignmnet from the user
        String assignment_name;
        System.out.print("Name your assignment: ");
        assignment_name = scanner.nextLine();

        //Get Due Date "02-05-2023" -> Feburary, 05, 2023
        String assignment_duedate = Helper_GetDueDate(scanner);

        //Description Of Assignment
        System.out.print("Set Assignment Description: ");
        String assignment_description = scanner.nextLine();

        String correct_answers = Helper_GetAssignmentAnswers(scanner);

        int ID = query.Helper_GetCourseIDFromTeacher(this);

        Assignment assignment = new Assignment(File, 
                                                ID,
                                                assignment_name, 
                                                assignment_duedate, 
                                                assignment_description,
                                                correct_answers);
        query.Faculty_PostAssignment(assignment, this);

        System.out.println(
            "Assignment: {" + assignment_name + "} Has Been Posted");
    }

    @Override
    public void ViewStudentGpa() {
        /*
            Gets a student from the database based id
            -
            Displays the students id to the terminal 
         */
        
    }


    //Takes in a list of students ids
    /* MIGHT NEED A HELPER UTILITY FUNCTION TO GET THE STUDENT IDS FIRST */
    @Override
    public void ViewStudentsGpa() {
        /*
            Gets Students 

         */
        
    }

    @Override
    public void GradeAssignment() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void EmailStudent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void EmailStudents() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ViewStudentAttendance() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void DeleteAssignment(Query query, int assignment_id) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void OpenAssignment(Query query, Scanner sc) throws SQLException
    {
        AssignmentView view = new AssignmentView();
        view.launchAssignmentView(query, this, sc);
    }

    @Override
    public void ShowCourses(Query query) throws SQLException
    {
        query.Faculty_ShowCourses(this);
    }
    //General Functions
    public course GetCourse() { return TeacherCourses; }

    //Helper Function
    private String Helper_GetDueDate(Scanner sc)
    {
        String DueDate;
        do{
            System.out.print("Enter Due Date (MM-DD-YYYY): ");
            DueDate = sc.nextLine();
        }while(!Helper_ValiateDueDate(DueDate));

        return DueDate;
    }

    private boolean Helper_ValiateDueDate(String date)
    {
        int max_date_length = 10;
        if(date.length() > max_date_length || date.length() < max_date_length)
            return false;
        if(Helper_DueDateSetUp(date)) 
            return false;
        return true;
    } 

    private boolean Helper_DueDateSetUp(String date)
    {
        boolean delimeter = false;
        for(int i = 0; i < date.length(); i++)
        {
            if(date.charAt(i) == '-' && date.charAt(i + 3) == '-') delimeter = true;
            if(i == 0) //Check Month 
            {
                int month = Integer.parseInt(date.substring(i, i+1));
                if(month > 1 || month < 12) return false;
            }
            if(i == 3) //Check Day
            {
                int day = Integer.parseInt(date.substring(i, i+1));
                if(day > 1 || day < 31) return false;
            }
        }
        return delimeter;
    }

    private String Helper_GetAssignmentAnswers(Scanner sc)
    {
        
        String response;
        int checked = 0;
        do{
            if(checked > 0) System.out.println("Only Allow abcd... answers");
            response = Helper_GetResponse(sc);
            checked++;
        }while(!Helper_ValidateAssignmentAnswers(response));

        return response;
    }

    private String Helper_GetResponse(Scanner sc)
    {
        System.out.println("Add Correct Answers To Assignment (e.g -> a,b,c,d,...):");
        String response = sc.nextLine();
        return response;
    }

    private boolean Helper_ValidateAssignmentAnswers(String s)
    {
        s = s.replace(" ", "");
        for(char c : s.toCharArray())
        {
            if(Character.isDigit(c))
                return false;
        }
        return true;
    }

}