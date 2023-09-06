package Source.HeadlessConfig.ConfigUser;

import java.sql.ResultSet;
import java.sql.SQLException;

import Source.UserFunctionalty.Faculty;
import Source.UserFunctionalty.Student;

public class ConfigUserFromDatabaseResult {
    Student student = null;
    Faculty faculty = null;
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
            student = new Student(UserName, UserType, UserEmail, UserId);
        
        else if ("teacher".equals(UserType))
            faculty = new Faculty(UserName, UserEmail, UserEmail, UserId);
            
        userinfo.close();
    }

    public Student getStudentFunctionality() { return student; }
    public Faculty getFacultyFunctionality() { return faculty; }

}
