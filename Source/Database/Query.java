package Source.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Source.CourseComponenets.Assignment;
import Source.UserFunctionalty.Faculty;
import Source.UserFunctionalty.User;

public class Query {
    //TODO: Finish Query Class

    Connection connection = null;
    DATABASECONNECTION DB = null;
    public Query(DATABASECONNECTION database)
    {
        DB = database;
        OpenConnection();
        InitConnection();
    }

    public void QueryShutdown()
    {
        CloseConnection();
    }

    //LOGIN FUNCTIONALITY
    public ResultSet Login_LogIntoDatabase(String HashedPassword, int ID) throws SQLException
    {
        String login_query = "SELECT * FROM users WHERE user_id = ? AND password = ?";
        PreparedStatement pStatement = connection.prepareStatement(login_query);
        pStatement.setInt(1, ID);
        pStatement.setString(2, HashedPassword);
        ResultSet UserData = pStatement.executeQuery();
        
        return UserData;
    }

    public void OpenConnection()
    {
        DB.SecureConnection();
    }

    public void CloseConnection()
    {
        DB.CloseConnection();
        System.out.println("Peace Out Fam!");
    }

    //Has to be used after a open connection function is made
    private void InitConnection()
    {
        connection = DB.GetConnection();
    }

    //ADMIN FUNCTIONALITY
    public void Admin_ShowUsers() throws SQLException
    {
        
        String query = "SELECT * From users";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        finally
        {
            while( resultSet.next() )
            {
                System.out.println("-------------------------------------");
                System.out.println("ID: { " + resultSet.getInt("user_id") + " }");
                System.out.println("TYPE: { " + resultSet.getString("user_type") + " }");
                System.out.println("NAME: { " + resultSet.getString("name") + " }");
                System.out.println("EMAIL: { " + resultSet.getString("email") + " }");
                System.out.println("-------------------------------------");
            }
        }
        
    }

    public void Admin_AddUser(User user) throws SQLException
    {
        OpenConnection();
        InitConnection();
        PreparedStatement pStatement = null;
        String insertQuery = "INSERT INTO users (user_id, user_type, name, email, password) VALUES (?, ?, ?, ?, ?)";

        try {
            pStatement = connection.prepareStatement(insertQuery);
        
            pStatement.setInt(1, user.getUserID());
            pStatement.setString(2, user.getUserType());
            pStatement.setString(3, user.getUserName());
            pStatement.setString(4, user.getUserEmail());
            pStatement.setString(5, user.getUserPassword());
        
            int rows = pStatement.executeUpdate();
        
            System.out.println(rows + " row(s) inserted into system");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement here if needed
        }
        
    }

    public void Admin_DeleteUser(User user) throws SQLException
    {
        
        String deleteQuery = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, user.getUserID());

        int rowsDeleted = preparedStatement.executeUpdate();
        System.out.println(rowsDeleted + " ros(s) deleted");
        
    }

    //FACULTY FUNCTIONALITY
    public void Faculty_GetGrades(){}

    public void Faculty_SetGrades(){}

    public void Faculty_ChangeGrades(){}

    public void Faculty_PostAssignment(Assignment assignment, Faculty user) throws SQLException 
    {
        String assignment_query = "INSERT INTO assignments (teacher_id, assignment_name, assignment_description, assignment_due_date, assignment_image) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStatement = connection.prepareStatement(assignment_query);

        pStatement.setInt(1, user.getUserID());
        pStatement.setInt(2, assignment.GetAssignmentID());
        pStatement.setString(3, assignment.GetAssignmentName());
        pStatement.setBytes(4, assignment.GetAssignmentImage());

        pStatement.executeUpdate();
    }

    public void Faculty_DeleteAssignmet(Assignment assignment, User user) throws SQLException {}

    public void Faculty_GradeAssignment(Assignment assignment, User user) throws SQLException {}




    

    
}
