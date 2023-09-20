package app.src.main.java.school.managemnet.system.Source.App.CourseComponenets;

public class course {
    private String Course_Name;
    private int Section_Number;

    public course(String name, int section)
    {
        Course_Name = name;
        Section_Number = section;
    }

    public void SetCourseName(String name) { Course_Name = name; }

    public void SetCourseNumber(int number) { Section_Number = number; }

    public int GetCourseID() { return Section_Number; }
    public String GetCourseName() { return Course_Name; }

}
