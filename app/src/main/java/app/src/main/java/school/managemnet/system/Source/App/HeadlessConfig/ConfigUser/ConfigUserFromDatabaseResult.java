package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student.StudentImpl;

public class ConfigUserFromDatabaseResult {
    StudentImpl student = null;
    FacultyImpl faculty = null;
    public String user_type = null;
    public ConfigUserFromDatabaseResult(ResultSet userinfo) throws SQLException
    {
        int UserId = -1; // ID undefined at this point
        String UserType = null;
        String UserName = null;
        String UserEmail = null;

        while(userinfo.next()){
            UserId = userinfo.getInt("user_id");
            UserType = userinfo.getString("user_type");
            UserName = userinfo.getString("name");
            UserEmail = userinfo.getString("email");
        }

        //TODO: Add functionality to get blob information (Assignments/email/etc) from the users

        user_type = UserType;
        if ("student".equals(UserType)) 
            student = new StudentImpl(UserName, UserType, UserEmail, UserId);
        
        else if ("teacher".equals(UserType))
            faculty = new FacultyImpl(UserName, UserEmail, UserEmail, UserId);
            
        userinfo.close();
    }

    public StudentImpl getStudent() { return student; }
    public FacultyImpl getFaculty() { return faculty; }

}
