package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;

public interface FacultyInterface {
    public void ViewStudentGpa();
    public void ViewStudentsGpa();
    public void CreateAssignment(QueryAPI query, Scanner scanner) throws SQLException;
    public void GradeAssignment();
    public void Contact(String subject, String message, DataTypes blob);
    public void ViewStudentAttendance();
    public void DeleteAssignment(QueryAPI query, int assignment_id) throws SQLException;
    public void OpenAssignment(QueryAPI query, Scanner sc) throws SQLException;
    public void ShowCourses(QueryAPI query) throws SQLException;
}
