package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.HeadlessStartUp;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class OptionFactory {

    static OptionsInterface Option;
    public static int FactoryMaxOptions = 9;
    private static OptionsHandler handler = new OptionsHandler(); 

    public static void FactoryRun(int option, User user)
    {
        handler.OptionHandler(option).ExecuteOption(user, HeadlessStartUp.HeadlessCustomType);
    }

    public static void Student_FactoryPrintOptions()
    {
        System.out.print(
                    "========================================\n" + 
                    "1: View Gpa\n" +
                    "2: Take Assignments\n" +
                    "3: See Grades\n" +
                    "4: Contact Teachers\n" +
                    "5: See Messages\n" +
                    FactoryMaxOptions +": Quit\n"+
                    "======================================== Enter Choice: " 
                    );
    }

    public static void Faculty_FactoryPrintOptions()
    {
        System.out.print(
                    "========================================\n" + 
                    "4: Contact Student\n" +
                    "5: See Messages\n" +
                    "6: Create Assignments\n" +
                    "7: See Courses\n" +
                    "8: View Assignments\n" +
                    FactoryMaxOptions +": Quit\n"+
                    "======================================== Enter Choice: " 
                    );

    }

}
