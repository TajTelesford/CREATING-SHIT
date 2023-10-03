package app.src.main.java.school.managemnet.system.Source.App.UserOptions;


import java.sql.SQLException;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Student_TakeAssignment extends OptionsInterface {

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        try {
            user.TakeAssignment(blob.getDQuery(), blob.getScanner());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
