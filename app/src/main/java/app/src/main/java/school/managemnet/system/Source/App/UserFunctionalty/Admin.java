package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty;

import java.sql.SQLException;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;

public class Admin extends User{
    public Admin(String name, String email, String password) throws SQLException
    {
        super("Admin", name, email, password);
    }

    public void DeleteUser(QueryAPI query, User user) throws SQLException
    {
        query.Admin_DeleteUser(user);
        System.out.println("Deleted that mf");
    }

    public void AddUser(QueryAPI query, User user) throws SQLException
    {
        query.Admin_AddUser(user);
        System.out.println("Added that mf");
    }

    public void ShowUser(QueryAPI query, User user) throws SQLException
    {
        query.Admin_ShowUsers();
        System.out.println("Print out all them mfs");
    }
}
