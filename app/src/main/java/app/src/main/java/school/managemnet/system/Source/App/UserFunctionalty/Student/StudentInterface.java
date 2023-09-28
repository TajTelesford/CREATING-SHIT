package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student;

import java.sql.SQLException;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.AssignmentType;

public interface StudentInterface {
    public void ViewGpa();
    public void TakeAssignment(Query query, Scanner sc);
    public void SubmitAssignment(Query query, Scanner sc, AssignmentType assignment) throws SQLException;
    public void SubmitAttendance();
    public void GetGrades();
    public void EmailTeacher();
    public void OpenAssignment(Query query, Scanner sc) throws SQLException;
}
