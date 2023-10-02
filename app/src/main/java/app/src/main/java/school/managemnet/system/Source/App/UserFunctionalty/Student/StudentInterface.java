package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.AssignmentType;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;

public interface StudentInterface {
    public void ViewGpa();
    public void TakeAssignment(QueryAPI query, Scanner sc);
    public void SubmitAssignment(QueryAPI query, Scanner sc, AssignmentType assignment) throws SQLException;
    public void SubmitAttendance();
    public void GetGrades();
    public void EmailTeacher();
    public void OpenAssignment(QueryAPI query, Scanner sc) throws SQLException;
}
