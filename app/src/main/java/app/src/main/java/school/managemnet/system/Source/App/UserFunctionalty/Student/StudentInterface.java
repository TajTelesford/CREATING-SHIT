package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;

public interface StudentInterface {
    public void ViewGpa();
    public void TakeAssignment();
    public void SubmitAssignmnet();
    public void SubmitAttendance();
    public void GetGrades();
    public void EmailTeacher();
    public void OpenAssignment(Query query, Scanner sc) throws SQLException;
}
