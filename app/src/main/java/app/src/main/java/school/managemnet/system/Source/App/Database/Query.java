package app.src.main.java.school.managemnet.system.Source.App.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.Assignment;
import app.src.main.java.school.managemnet.system.Source.App.CourseComponenets.course;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;

class UserIdtypes {
    String Type;
    int ID;
}

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

    public void Admin_MakeCourses(Scanner sc) throws SQLException
    {
        /*
         * Find All Teachers
         * Select A Teacher
         * Give Them A Course(s)
         */

        List<FacultyImpl> ListOfTeachers = new ArrayList<>();
        //Teachers = 1
        //Students = 2
        String query = "SELECT * FROM users WHERE user_type = 1";
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
                int id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                //We Don't Need The Passwords To Be Used {(Password) -> null}
                ListOfTeachers.add(new FacultyImpl(name, email, null, id)); 
            }
        }
        resultSet.close();
        //Select A Teacher
        for(int i = 0; i < ListOfTeachers.size(); i++)
        {
            System.out.println(i+1 + ": " + ListOfTeachers.get(i).getUserName());
        }
        System.out.print("Select Teacher: ");

        //TODO: Create Validation For User Selection
        int choice = sc.nextInt() - 1;

        //Give Teacher A Course(s)
        System.out.print("Name Of Course: ");
        sc.nextLine();
        String course_name = sc.nextLine();
        Courses_ConnectFacultyToCourse(ListOfTeachers.get(choice), course_name);
        System.out.println();

    }
    //FACULTY FUNCTIONALITY
    public void Faculty_ShowCourses(FacultyImpl user) throws SQLException
    {
        String query = "SELECT * FROM courses WHERE teacher_id = ?";
        PreparedStatement pStatement = connection.prepareStatement(query);

        pStatement.setInt(1, user.getUserID());

        ResultSet resultSet = pStatement.executeQuery();
        System.out.println("====================================");
        while( resultSet.next() )
        {
            int course_id = resultSet.getInt("course_id");
            String course_name = resultSet.getString("course_name");
            System.out.println("Course: " + course_name + " Section #: " + course_id);
        }
        System.out.println("====================================");
        
    }

    public void Faculty_GetGrades(){}

    public void Faculty_SetGrades(){}

    public void Faculty_ChangeGrades(){}

    public void Faculty_PostAssignment(Assignment assignment, FacultyImpl user) throws SQLException 
    {
        String assignment_query = "INSERT INTO assignments (assignment_id, course_id, assignment_name,"+
                                    "assignment_description, correct_answers, due_date," +
                                    "assignment_image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatement = connection.prepareStatement(assignment_query);

        pStatement.setInt(1, assignment.GetAssignmentID());
        pStatement.setInt(2, Helper_GetCourseIDFromTeacher(user));
        pStatement.setString(3, assignment.GetAssignmentName());
        pStatement.setString(4, assignment.GetAssignmentDescription());
        pStatement.setString(5, assignment.GetAssignmentCorrectAnswers());
        pStatement.setString(6, assignment.GetAssignmentDueDate());
        pStatement.setBytes(7, assignment.GetAssignmentImage());

        pStatement.executeUpdate();
    }

    public void Faculty_DeleteAssignmet(Assignment assignment, User user) throws SQLException {}

    public void Faculty_GradeAssignment(Assignment assignment, User user) throws SQLException {}

    //Course Functionality
    public void Courses_ConnectFacultyToCourse(FacultyImpl teacher, String name) throws SQLException
    {
        String query = "INSERT INTO courses (course_id, teacher_id, course_name) VALUES (?, ?, ?);";
        PreparedStatement pStatement = connection.prepareStatement(query);

        //Create a better course Id generator
        pStatement.setInt(1, new Random().nextInt(10000000));
        pStatement.setInt(2, teacher.getUserID());
        pStatement.setString(3, name);

        int rowsAffected = pStatement.executeUpdate();
        System.out.println("ROWS AFFECTED: " + rowsAffected);
    }

    //General Functions
    public byte[] OpenAssignment(FacultyImpl teacher, Scanner sc) throws SQLException 
    {
        /*
         * Get a list of all assignments a teacher has
         * User picks a assignment to open
         * Fetches the bytes from the LONGBLOB Image 
         * Returns the bytes to the caller
         */
        String teacher_query = "SELECT * FROM courses WHERE teacher_id = ?";
        PreparedStatement pStatement = connection.prepareStatement(teacher_query);

        pStatement.setInt(1, teacher.getUserID());

        ResultSet resultSet = pStatement.executeQuery();
        List<course> courseId_list = new ArrayList<>();
        int choice, i = 0;
        System.out.println("Which Course Is The Assignment In?: ");
        while( resultSet.next() )
        {
            int course_id = resultSet.getInt("course_id");
            String course_name = resultSet.getString("course_name");
            System.out.println("Course "+ i+1 + ": " + course_name + " Section #: " + course_id);
            courseId_list.add(new course(course_name, course_id));
            i++;
        }
        //Add Validation HERE
        choice = sc.nextInt();
        course selected_course = courseId_list.get(choice - 1);
        Assignment assignment = Helper_GetAssignmentFromCourse(selected_course.GetCourseID(), teacher.getUserID(), sc);

        return assignment.GetAssignmentImage(); //returns image bytes
    }


    //Helper Functions
    public int Helper_GetCourseIDFromTeacher(FacultyImpl teacher) throws SQLException
    {
        int ID = -1;
        String query = "SELECT course_id FROM courses WHERE teacher_id = ?";
        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, teacher.getUserID());

        ResultSet resultSet = pStatement.executeQuery();
        while( resultSet.next() )
        {
            ID = resultSet.getInt("course_id");
        }
        return ID;
    }

    private Assignment Helper_GetAssignmentFromCourse(int course_id, int teacher_id, Scanner sc) throws SQLException
    {
        String query = "Select * from assignments where course_id = ?";
        List<Assignment> assignments = new ArrayList<>();

        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, course_id);

        ResultSet resultSet = pStatement.executeQuery();

        int i = 0, choice;
        
        System.out.println("========================================");
        while( resultSet.next() )
        {
            String name = resultSet.getString("assignment_name");
            System.out.println(i + 1 + ": " + name);
            Assignment assignment = new Assignment(
                resultSet.getBytes("assignment_image"), 
                resultSet.getInt("assignment_id"),
                resultSet.getInt("course_id"),
                name,
                resultSet.getString("assignment_description"),
                resultSet.getString("due_date"),
                resultSet.getString("correct_answers")
            );
            assignments.add(assignment);
        }
        resultSet.close();
        System.out.println("========================================");
        System.out.print("Which Assignment?: ");
        //ADD VALIDATION HERE
        choice = sc.nextInt();

        return assignments.get(choice-1);
    }
}
