package Source.HeadlessConfig.ConfigUser;

public class ConfigUserUtils {
    public static void WelcomeUserFunction(ConfigUserFromDatabaseResult res)
    {
        if(res.getFacultyFunctionality() == null) //res is a student object
            System.out.println("Welcome, " + 
                        res.getStudentFunctionality().getUserName() + 
                        "! User Type: " +
                        res.user_type.toUpperCase());
        if(res.getStudentFunctionality() == null) //res is a teacher object
            System.out.println("Welcome, " + 
                res.getFacultyFunctionality().getUserName() + 
                "! User Type: " +
                res.user_type.toUpperCase());
    }
}
