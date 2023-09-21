package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student.StudentImpl;

public interface UserInterface {
    String getUserType();
    int getUserID();
    String getUserName();
    String getUserEmail();
    String getUserPassword();
    StudentImpl ReturnAStudent(User user);
    FacultyImpl ReturnAFaculty(User user) throws SQLException;
    void CreateID();
    boolean IDCreationCheck(int id, final int[] IDCacheList);

    //Shared Functions
    public void OpenAssignment(Query query, Scanner sc) throws SQLException;
    public void ShowCourses(Query query) throws SQLException;
    
    //Student
    public void ViewGpa();
    public void TakeAssignment();
    public void SubmitAssignmnet();
    public void SubmitAttendance();
    public void GetGrades();
    public void EmailTeacher();

    //Faculty
    public void ViewStudentGpa();
    public void ViewStudentsGpa();
    public void CreateAssignment(Query query, Scanner scanner) throws SQLException;
    public void GradeAssignment();
    public void ViewStudentAttendance();
    public void EmailStudent();
    public void EmailStudents();
    public void DeleteAssignment(Query query, int assignment_id) throws SQLException;
    

}
