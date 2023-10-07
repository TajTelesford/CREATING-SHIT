package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Student_ShowCourses extends OptionsInterface {
    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        blob.getDQuery().Helper_ShowCourses(user.getUserID());
    }
}