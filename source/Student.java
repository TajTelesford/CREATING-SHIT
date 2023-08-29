package source;


public class Student extends User{

    private Integer AvgGrade;
    
    public Student(String name)
    {
        super("Student", name);
    }

    public Integer AvgGrade()
    {
        return AvgGrade;
    }
}
