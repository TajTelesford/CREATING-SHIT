package Source.UserFunctionalty;


public class Student extends User{

    private Integer GPA;
    
    public Student(String name, String email, String password)
    {
        super("Student", name, email, password);
    }

    public Integer AvgGrade()
    {
        return GPA;
    }
    //IMPLEMENT
    /*
        - Take assignments
            - Pull assignments from database
            - (Get Teacher ID from database)
        - Submit assignments
        - Submit attendance
        - See their grades
        - Email teachers
     */
}
