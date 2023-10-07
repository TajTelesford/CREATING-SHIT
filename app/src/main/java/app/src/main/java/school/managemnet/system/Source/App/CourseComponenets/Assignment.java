package app.src.main.java.school.managemnet.system.Source.App.CourseComponenets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;

public class Assignment {
    private boolean Assignmnet_Taken = false;
    private int Assignment_CourseId;
    private int Assignment_TeacherId;
    private int Assignment_Id;
    private String Assignment_Name;
    private String Assignment_Description;
    private String Correct_Answers;
    private String Assignment_DueDate;
    private String Assignment_SubmissionDate;
    private byte[] Assignment_Image;

    public Assignment(String f, int course_id, String name, String DueDate, String Description, String Answers)
    {
        setImageFromFile(f);
        Assignment_CourseId = course_id;
        Assignment_Name = name;
        Assignment_DueDate = DueDate;
        Assignment_Description = Description;
        Correct_Answers = Answers;
        Assignment_SubmissionDate = null;
        CreateAssignmentID();
    }   

    public Assignment(byte[] assignment_image, int id, int course_id, String name, String DueDate, String Description, String Answers)
    {
        Assignment_Image = assignment_image;
        Assignment_Id = id;
        Assignment_CourseId = course_id;
        Assignment_Name = name;
        Assignment_DueDate = DueDate;
        Assignment_Description = Description;
        Correct_Answers = Answers;
    }

    public Assignment(int id, int course_id, String name, String answers)
    {
        Assignment_Id = id;
        Assignment_CourseId = course_id;
        Assignment_Name = name;
        Correct_Answers = answers;
    }

    /*
     * Pre: Checks To See If The File Exists
     * Post: Takes A File (PNG. Or JPG) And Converts The Image Into A
     *       Byte Array Before Pushing Data Into Database
     */
    public void setImageFromFile(String file)
    {
        try {
            File image = new File(file);
            FileInputStream f_InputStream = new FileInputStream(image);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while( (bytesRead = f_InputStream.read(buffer)) != -1 )
                byteArrayOutputStream.write(buffer, 0, bytesRead);

            Assignment_Image = byteArrayOutputStream.toByteArray();
            f_InputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Pre: (None)
     * Post: Returns A Byte Array To Represent The Image
     */
    public InputStream getImageInputStream()
    {
        return new ByteArrayInputStream(Assignment_Image);
    }

    /*
     * Pre: (None)
     * Post: Takes In A Faculty Object And Sets The Teacher ID
     *       From The Faculty Object
     */
    public void SetTeacherID(FacultyImpl teacher)
    {
        Assignment_TeacherId = teacher.getUserID();
    }

    //Fix This Function got super lazy
    public void CreateAssignmentID()
    {
        Random r = new Random();
        Assignment_Id = r.nextInt(10000000);
    }

    public void SetAssignmentName(String name)
    {
        Assignment_Name = name;
    }

    /*
        Pre: Validate Date
        Post: Sets Due Date before database submission
    */
    public void SetDueDateForAssignment(String date)
    {
        Assignment_DueDate = date;
    }

    /* 
     * Pre: (None)
     * Post: On Creation of a Assignment Instance Creates A
     *       ID For The Instance 
    */
    public int GetAssignmentID() { return Assignment_Id; }

    public int GetAssignmnetTeacherID() { return Assignment_TeacherId; }

    public int GetAssignmentCourseID() { return Assignment_CourseId; }

    public String GetAssignmentName() { return Assignment_Name; }

    public String GetAssignmentDescription() { return Assignment_Description; }

    public String GetAssignmentCorrectAnswers() { return Correct_Answers; }

    public String GetAssignmentDueDate() { return Assignment_DueDate; }

    public String GetAssignmnetSubmissionDate() { return Assignment_SubmissionDate; }

    public boolean AssignmentTaken() { return Assignmnet_Taken; }

    public byte[] GetAssignmentImage() { return Assignment_Image; }


    /*
        Variables:
        - Student Name
        - Student ID (Primary Key - Database)
        - Date Of Submittion
        - Assignment informarion (Questions - Answers)
        
     */
}
