package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions;


import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class OptionFactory {

    static OptionsInterface Option;
    public static int FactoryMaxOptions = 9;

    public static void FactoryRun(int option, User user, DataTypes blob)
    {
        ConfigOption(option).ExecuteOption(user, blob);
    }

    //TODO: FIX THIS
    private static int GetNumOfAllImplementations() {
        Class<OptionsInterface> OptionsInterface = OptionsInterface.class;
        int count = 0;

        Class<?>[] allImplementations = OptionsInterface.getClasses();
        for(Class<?> c : allImplementations)
        {
            if(OptionsInterface.isAssignableFrom(c) && !c.equals(OptionsInterface)) count++;
        }
        return count;
    }

    //configures option
    private static OptionsInterface ConfigOption(int option)
    {
        switch(option)
        {
            case 1:
                Option = new Student_ViewGpa();
                break;
            case 2:
                Option = new Student_TakeAssignment();
                break;
            case 3:
                Option = new Student_SeeGrades();
                break;
            case 4:
                Option = new Contact();
                break;
            case 5:
                Option = new SeeMessages();
                break;
            case 6:
                Option = new Faculty_CreateAssignments();
                break;
            case 7:
                Option = new Faculty_ShowCourses();
                break;
            case 8:
                Option = new Faculty_OpenAssignments();
                break;
            default:
                Option = new NotValidOption();

        }
        return Option;
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
