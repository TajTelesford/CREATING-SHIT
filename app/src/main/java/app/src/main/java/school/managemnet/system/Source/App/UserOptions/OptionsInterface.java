package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

//TODO:FINISH ALL OPTIONS

//Allows All SubClasses To Excute Their Option
public abstract class OptionsInterface {
    public void ExecuteOption(User user, DataTypes blob){}
}
