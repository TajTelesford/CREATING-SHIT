package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.UserOptions;

import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class OptionFactory {

    static OptionsInterface Option;
    public static int FactoryMaxOptions = 10;

    public static void FactoryRun(int option, User user, DataTypes blob)
    {
        ConfigOption(option).ExecuteOption(user, blob);
    }

    //configures option
    private static OptionsInterface ConfigOption(int option)
    {
        System.out.println(option);
        switch(option)
        {
            case 1:
                Option = new Student_ViewGpa();
                break;
            case 2:
                Option = new Student_TakeAssignment();
                break;
            case 3:
                Option = new Student_SubmitAttendance();
                break;
            case 4:
                Option = new Student_SeeGrades();
                break;
            case 5:
                Option = new Student_EmailFaculty();
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
            case 9:
                Option = new Faculty_EmailStudents();
                break;
            default:
                 

        }
        return Option;
    }

    public static void Student_FactoryPrintOptions()
    {
        System.out.print(
                    "========================================\n" + 
                    "1: View Gpa\n" +
                    "2: Take Assignments\n" +
                    "3: Submit Attendence\n" +
                    "4: See Grades\n" +
                    "5: Email Teachers\n" +
                    FactoryMaxOptions +": Quit\n"+
                    "======================================== Enter Choice: " 
                    );
    }

    public static void Faculty_FactoryPrintOptions()
    {
        System.out.print(
                    "========================================\n" + 
                    "6: Create Assignments\n" +
                    "7: Show Courses\n" +
                    "8: Open An Assignment\n" +
                    "9: Email Students\n" +
                    FactoryMaxOptions +": Quit\n"+
                    "======================================== Enter Choice: " 
                    );

    }

}
