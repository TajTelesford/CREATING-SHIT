package Source.CourseComponenets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import Source.UserFunctionalty.Faculty;

public class Assignment {
    private boolean Assignmnet_Taken = false;
    private int Assignment_TeacherId;
    private int Assignment_Id;
    private String Assignment_Name;
    private String Assignmnet_Description;
    private String Assignment_DueDate;
    private String Assignment_SubmissionDate;
    private byte[] Assignment_Image;

    public Assignment(String f, String name, String DueDate, String Description)
    {
        setImageFromFile(f);
        Assignment_Name = name;
        Assignment_DueDate = DueDate;
        Assignmnet_Description = Description;
        Assignment_SubmissionDate = null;
        CreateAssignmentID();
    }   

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

    public InputStream getImageInputStream()
    {
        return new ByteArrayInputStream(Assignment_Image);
    }

    public void SetTeacherID(Faculty teacher)
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

    public void SetDueDateForAssignment(String date)
    {
        Assignment_DueDate = date;
    }

    public int GetAssignmentID() { return Assignment_Id; }

    public String GetAssignmentName() { return Assignment_Name; }

    public String GetAssignmentDescription() { return Assignmnet_Description; }

    public String GetAssignmentDueDate() { return Assignment_DueDate; }

    public byte[] GetAssignmentImage() { return Assignment_Image; }


    /*
        Variables:
        - Student Name
        - Student ID (Primary Key - Database)
        - Date Of Submittion
        - Assignment informarion (Questions - Answers)
        
     */
}
