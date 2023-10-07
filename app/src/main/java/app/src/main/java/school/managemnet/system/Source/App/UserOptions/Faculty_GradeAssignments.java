package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class Faculty_GradeAssignments extends OptionsInterface {
    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        user.GradeAssignment(blob);
    }
}
