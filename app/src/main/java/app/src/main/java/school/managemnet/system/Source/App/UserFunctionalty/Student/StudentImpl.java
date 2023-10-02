package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.NotImplemented;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.Assignment;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.AssignmentView;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.AssignmentType;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.MessageType;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.MessageProtocol.MessageAPI;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;

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
        NotImplemented.Todo();
    }

    @Override
    public void TakeAssignment(QueryAPI query, Scanner sc) 
    {
        AssignmentView view = new AssignmentView();
        try {
            view.launchAssignmentView(query, this, sc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String GetStudentAnswers(Scanner sc)
    {
        String student_answers;
        System.out.println("ANSWER LIKE THIS: (e.g -> 1.a 2.b 3.c ...) ");
        System.out.println("IF DONE PRESS ENTER, IF NOT DON'T PRESS ENTER");
        System.out.print("Answer ->: ");
        student_answers = sc.nextLine();
        
        return student_answers;
    }

    @Override
    public void SubmitAssignment(QueryAPI query, Scanner sc, AssignmentType assignment) throws SQLException 
    {
        int course_id = QueryAPI.Helper_GetCourseIDFromAssignment(assignment);
        query.Student_SubmitAssignment(this, course_id, GetStudentAnswers(sc), assignment);
    }

    @Override
    public void GetGrades() {
        NotImplemented.Todo();
    }

    @Override
    public void SubmitAttendance() {
        NotImplemented.Todo();
        
    }

    @Override
    public void Contact(String subject, String message, DataTypes blob) {
        List<FacultyImpl> teacher_list = blob.getDQuery().Helper_GetListOfTeachers(this);

        int id = GetTeacherFromList(teacher_list, blob.getScanner());

            MessageType messageBlob = new MessageType(this.getUserID(), id, this.getUserName(), subject, message);
            MessageAPI.SendMessage(messageBlob, this);
            blob.getScanner().nextLine();
    }

    private int GetTeacherFromList(List<FacultyImpl> list, Scanner scanner) {
        System.out.println("Which Teacher: ");
        PrintTeachers(list);
        //Add Validation
        int choice = scanner.nextInt() - 1;

        return list.get(choice).getUserID();
	}

	private void PrintTeachers(List<FacultyImpl> list) {
        System.out.println("=============================\n");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(
                "(" + (i+1) + ")\n" +
                "Name: " + list.get(i).getUserName() + "\n" +
                "Email: " + list.get(i).getUserEmail() 
            );
        }
        System.out.println("\n");
        System.out.println("=============================");
    }

    @Override
    public void OpenAssignment(QueryAPI query, Scanner sc) throws SQLException 
    {
        AssignmentView view = new AssignmentView();
        view.launchAssignmentView(query, this, sc);
        sc.nextLine();
    }

	
}
