package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Faculty_EmailStudents implements OptionsInterface {

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        user.EmailStudents();
    }
    
}
