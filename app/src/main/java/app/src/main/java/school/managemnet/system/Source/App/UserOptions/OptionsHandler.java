package app.src.main.java.school.managemnet.system.Source.App.UserOptions;
import java.util.HashMap;
import java.util.Map;

public class OptionsHandler{
    private static Map<Integer, OptionsInterface> OptionsMap;
    
    public OptionsHandler()
    {
        OptionsMap = new HashMap<>();

        OptionsMap.put(1, new Student_ViewGpa());
        OptionsMap.put(2, new Student_TakeAssignment());
        OptionsMap.put(3, new Student_SeeGrades());
        OptionsMap.put(4, new Contact());
        OptionsMap.put(5, new SeeMessages());
        OptionsMap.put(6, new Faculty_CreateAssignments());
        OptionsMap.put(7, new Faculty_ShowCourses());
        OptionsMap.put(8, new Faculty_OpenAssignments());
    }

    public static OptionsInterface OptionHandler(int OptionChoice)
    {
        return OptionsMap.get(OptionChoice);
    }
}