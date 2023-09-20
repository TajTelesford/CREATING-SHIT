package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;

public interface FacultyInterface {
    public void ViewStudentGpa();
    public void ViewStudentsGpa();
    public void CreateAssignment(Query query, Scanner scanner) throws SQLException;
    public void GradeAssignment();
    public void ViewStudentAttendance();
    public void EmailStudent();
    public void EmailStudents();
    public void DeleteAssignment(Query query, int assignment_id) throws SQLException;
    public void OpenAssignment(Query query, Scanner sc) throws SQLException;
    public void ShowCourses(Query query) throws SQLException;
}
