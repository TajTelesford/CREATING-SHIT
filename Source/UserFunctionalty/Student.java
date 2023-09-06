package Source.UserFunctionalty;

import java.util.ArrayList;
import java.util.List;

import Source.CourseComponenets.Assignment;

public class Student extends User{
    private List<Assignment> StudentAssignments;
    private List<Assignment> StudentAssignmentsTaken; 
    private Integer GPA;
    
    public Student(String name, String email, String password)
    {
        super("student", name, email, password);
        //Fetch Not Completed Assignments from database
        StudentAssignments = new ArrayList<Assignment>();
        StudentAssignmentsTaken = new ArrayList<Assignment>();
    }

    public Student(String name, String email, String password, int id)
    {
        super("student", name, email, password, id);
    }

    

    public Integer Gpa()
    {
        return GPA;
    }
    //IMPLEMENT
    /*
        - Take assignments
            - Pull assignments from database
            - (Get Teacher ID from database)
        - Submit assignments
        - Submit attendance
        - See their grades
        - Email teachers
     */
}
