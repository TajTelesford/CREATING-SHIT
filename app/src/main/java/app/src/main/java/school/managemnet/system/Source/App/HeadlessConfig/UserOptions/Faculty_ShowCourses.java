package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions;

import java.sql.SQLException;

import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Faculty_ShowCourses implements OptionsInterface {

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        try {
            user.ShowCourses(blob.getDQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
