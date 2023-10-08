package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.NotImplemented;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Student_SeeGrades extends OptionsInterface{

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        blob.getDQuery().Student_SeeGrades(user.ReturnAStudent(user));
    }
    
}
