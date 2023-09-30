package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.AssignmentType;
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
    public void SetCurrentPassword(String password2);

    //Shared Functions
    public void OpenAssignment(QueryAPI query, Scanner sc) throws SQLException;
    public void ShowCourses(QueryAPI query) throws SQLException;
    
    //Student
    public void ViewGpa();
    public void TakeAssignment(QueryAPI query, Scanner sc) throws SQLException;
    public void SubmitAssignment(QueryAPI query, Scanner sc, AssignmentType assignment) throws SQLException;
    public void SubmitAttendance();
    public void GetGrades();
    public void EmailTeacher();

    //Faculty
    public void ViewStudentGpa();
    public void ViewStudentsGpa();
    public void CreateAssignment(QueryAPI query, Scanner scanner) throws SQLException;
    public void GradeAssignment();
    public void ViewStudentAttendance();
    public void EmailStudent();
    public void EmailStudents();
    public void DeleteAssignment(QueryAPI query, int assignment_id) throws SQLException;
    

}
