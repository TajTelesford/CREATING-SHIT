package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

//TODO:FINISH ALL OPTIONS

//Allows All SubClasses To Excute Their Option
public interface OptionsInterface {
    public void ExecuteOption(User user, DataTypes blob);
}
