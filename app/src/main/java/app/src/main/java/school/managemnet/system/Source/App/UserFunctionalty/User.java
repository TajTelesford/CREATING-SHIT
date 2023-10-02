package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Application.AppStart;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.AssignmentType;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
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
    public void SetCurrentPassword(String password2) {
        this.Password = password2;
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

    //ALL USERS HAVE THESE FUNCTIONS, THE STUDENTS AND FACULTY HAVE THEIR OWN IMPLEMENTATIONS
    @Override
    public void OpenAssignment(QueryAPI query, Scanner sc) throws SQLException {
        
    }

    @Override
    public void ShowCourses(QueryAPI query) throws SQLException {
        
    }

    @Override
    public void ViewGpa() {
        
    }

    @Override
    public void TakeAssignment(QueryAPI query, Scanner sc) throws SQLException {
        
    }

    @Override
    public void SubmitAssignment(QueryAPI query, Scanner sc, AssignmentType assignment) throws SQLException
    {
         
    }


    @Override
    public void GetGrades() {
       
    }

    @Override
    public void EmailTeacher() {
       
    }

    @Override
    public void ViewStudentGpa() {
         
    }

    @Override
    public void ViewStudentsGpa() {
         
    }

    @Override
    public void CreateAssignment(QueryAPI query, Scanner scanner) throws SQLException {
         
    }

    @Override
    public void GradeAssignment() {
         
    }

    @Override
    public void ViewStudentAttendance() {
        
    }

    @Override
    public void EmailStudent() {
         
    }

    @Override
    public void EmailStudents() {
     
    }

    @Override
    public void DeleteAssignment(QueryAPI query, int assignment_id) throws SQLException {
         
    }

    @Override
    public void SubmitAttendance() {
    
    }

    
}
