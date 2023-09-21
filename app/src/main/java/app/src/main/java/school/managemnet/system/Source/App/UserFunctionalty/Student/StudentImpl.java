package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.Assignment;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.AssignmentView;
import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class StudentImpl extends User implements StudentInterface{
    private List<Assignment> StudentAssignments;
    private List<Assignment> StudentAssignmentsTaken; 
    private Integer GPA;
    
    public StudentImpl(String name, String email, String password)
    {
        super("student", name, email, password);
        //Fetch Not Completed Assignments from database
        StudentAssignments = new ArrayList<Assignment>();
        StudentAssignmentsTaken = new ArrayList<Assignment>();
    }

    public StudentImpl(String name, String email, String password, int id)
    {
        super("student", name, email, password, id);
    }

    public Integer GetGpa()
    {
        return GPA;
    }

    public List<Assignment> GetAssignmnets() { return StudentAssignments; }

    public List<Assignment> GetAssignmnetsTaken() { return StudentAssignmentsTaken; }
    
    @Override
    public void ViewGpa() {
        // TODO Auto-generated method stub
    }

    @Override
    public void TakeAssignment() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void SubmitAssignmnet() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void GetGrades() {
        
    }

    @Override
    public void SubmitAttendance() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void EmailTeacher() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void OpenAssignment(Query query, Scanner sc) throws SQLException 
    {
        AssignmentView view = new AssignmentView();
        view.launchAssignmentView(query, null, this, sc);
        sc.nextLine();
    }
}
