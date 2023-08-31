package Source;


public class Student extends User{

    private Integer GPA;
    
    public Student(String name)
    {
        super("Student", name);
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
