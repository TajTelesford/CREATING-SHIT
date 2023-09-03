package Source.CourseComponenets;

public class Courses {
    
    
    private course[] CourseInformation;

    public Courses(course[] courses)
    {
        CourseInformation = courses;
    }

    /*
        - Display Courses and course assignments
        - 
     */
    public void DisplayCourses()
    {
        for( int i = 0; i < CourseInformation.length; i++ )
        {
            System.out.println(CourseInformation[i]);
        }
    }
}
