package app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser;

public class ConfigUserUtils {
    public static void WelcomeUserFunction(ConfigUserFromDatabaseResult res)
    {
         if(res.getFaculty() == null) //res is a student object
            System.out.println("Welcome, " + 
                        res.getStudent().getUserName() + 
                        "! User Type: " +
                        res.user_type.toUpperCase());
        else if(res.getStudent() == null) //res is a teacher object
            System.out.println("Welcome, " + 
                res.getFaculty().getUserName() + 
                "! User Type: " +
                res.user_type.toUpperCase());
       
    }

    public static void FailedLoginFunction() {
    }
}
