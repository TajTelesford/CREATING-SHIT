package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Application.AppStart;
import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student.StudentImpl;
public class User implements UserInterface
{
    private String UserType;
    private int ID;
    private String Name;
    private String Email;
    private String Password;
    static public int MAX_NUMBER_OF_IDS = 1000000;

    public enum UserTypes {
        Teacher, Student
    }

    public User(String type, String name, String email, String password)
    {
        UserType = type;
        Name = name;
        Email = email;
        Password = password;
        CreateID();
    }

    //Constructor for already made user from database
    public User(String type, String name, String email, String password, int id)
    {
        UserType = type;
        Name = name;
        Email = email;
        Password = password;
        ID = id;
    }

    @Override
    public StudentImpl ReturnAStudent(User user)
    {
        return new StudentImpl(user.Name, user.Email, user.Password, user.ID);
    }

    @Override
    public FacultyImpl ReturnAFaculty(User user) throws SQLException
    {
        return new FacultyImpl(user.Name, user.Email, user.Password, user.ID);
    }

    static int RandomNumber()
    {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER_OF_IDS);
    }

    @Override
    public void CreateID()
    {
        int checkID = RandomNumber();
        boolean c = IDCreationCheck(checkID, AppStart.Application_UserIDS);
        do {
            checkID = RandomNumber();
            c = IDCreationCheck(checkID, AppStart.Application_UserIDS);
        } while (!c);
        ID = checkID;
    } 
    
    @Override
    public boolean IDCreationCheck(int id, final int[] IDCacheList)
    {
        if(IDCacheList.length < 0) AppStart.Application_UserIDS[0] = id;

        for(int i = 0; i < IDCacheList.length; i++)
        {
            if(id == IDCacheList[i]) return false;
        }
        return true;
    }

    @Override
    public String getUserType(){ return UserType; }

    @Override
    public int getUserID(){ return ID; }

    @Override
    public String getUserName(){ return Name; }

    @Override
    public String getUserEmail(){ return Email; }

    @Override
    public String getUserPassword(){ return Password; }

    @Override
    public void OpenAssignment(Query query, Scanner sc) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'OpenAssignment'");
    }

    @Override
    public void ShowCourses(Query query) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ShowCourses'");
    }

    @Override
    public void ViewGpa() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ViewGpa'");
    }

    @Override
    public void TakeAssignment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TakeAssignment'");
    }

    @Override
    public void SubmitAssignmnet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SubmitAssignmnet'");
    }

    @Override
    public void SubmitAttendance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SubmitAttendance'");
    }

    @Override
    public void GetGrades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetGrades'");
    }

    @Override
    public void EmailTeacher() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EmailTeacher'");
    }

    @Override
    public void ViewStudentGpa() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ViewStudentGpa'");
    }

    @Override
    public void ViewStudentsGpa() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ViewStudentsGpa'");
    }

    @Override
    public void CreateAssignment(Query query, Scanner scanner) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CreateAssignment'");
    }

    @Override
    public void GradeAssignment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GradeAssignment'");
    }

    @Override
    public void ViewStudentAttendance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ViewStudentAttendance'");
    }

    @Override
    public void EmailStudent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EmailStudent'");
    }

    @Override
    public void EmailStudents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EmailStudents'");
    }

    @Override
    public void DeleteAssignment(Query query, int assignment_id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteAssignment'");
    }
}
