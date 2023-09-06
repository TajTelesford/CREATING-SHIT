package Source.UserFunctionalty;

import java.sql.SQLException;

import Source.Database.Query;

public class Admin extends User{
    public Admin(String name, String email, String password)
    {
        super("Admin", name, email, password);
    }

    public void DeleteUser(Query query, User user) throws SQLException
    {
        query.Admin_DeleteUser(user);
        System.out.println("Deleted that mf");
    }
}
