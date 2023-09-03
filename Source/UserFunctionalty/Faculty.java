package Source.UserFunctionalty;

public class Faculty extends User{
    public Faculty(String name, String email, String password)
    {
        super("Student", name, email, password);
    }
    /*  
        - Create Assignments
            - Push Assignments to database
        - Grade Assignments
            - Pull Assignments info from students and grade it
            - Have a Incomplete, In-Progress, Complete, UnSubmitted
        - See student attendance
        - Submit grades to students
        - Email students
    */

}