package app.src.main.java.school.managemnet.system.Source.App.UserOptions;

import java.util.List;
import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.MessageType;
import app.src.main.java.school.managemnet.system.Source.App.MessageProtocol.MessageAPI;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student.StudentImpl;

public class Contact implements OptionsInterface{

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        if(user.getUserType().equals("student")){
            String subject = GetSubjectContext(blob.getScanner());

            String message = GetMessageContext(blob.getScanner());
        //Pick A Teacher
            List<FacultyImpl> teacher_list = blob.getDQuery().Helper_GetListOfTeachers(user);

            int id = GetTeacherFromList(teacher_list, blob.getScanner());

            MessageType messageBlob = new MessageType(user.getUserID(), id, user.getUserName(), subject, message);
            MessageAPI.SendMessage(messageBlob, user);
            blob.getScanner().nextLine();
        }
        else{
            List<StudentImpl> student_list = blob.getDQuery().Helper_GetListOfStudents(user);

            String subject = GetSubjectContext(blob.getScanner());
            String message = GetMessageContext(blob.getScanner());
            int id = GetStudentFromList(student_list, blob.getScanner());

            MessageType messageBlob = new MessageType(user.getUserID(), id, user.getUserName(), subject, message);
            MessageAPI.SendMessage(messageBlob, user);
            blob.getScanner().nextLine();

        }
    }

    private int GetTeacherFromList(List<FacultyImpl> list, Scanner sc)
    {
        System.out.println("Which Teacher: ");
        PrintTeachers(list);
        //Add Validation
        int choice = sc.nextInt() - 1;

        return list.get(choice).getUserID();
    }

    private int GetStudentFromList(List<StudentImpl> list, Scanner sc)
    {
        System.out.println("Which Teacher: ");
        PrintStudents(list);
        //Add Validation
        int choice = sc.nextInt() - 1;

        return list.get(choice).getUserID();
    }

    private void PrintStudents(List<StudentImpl> list) 
    {
        System.out.println("=============================\n");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(
                "(" + (i+1) + ")\n" +
                "Name: " + list.get(i).getUserName() + "\n" +
                "Email: " + list.get(i).getUserEmail() 
            );
        }
        System.out.println("\n");
        System.out.println("=============================");
    }

    private void PrintTeachers(List<FacultyImpl> list)
    {
        System.out.println("=============================\n");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(
                "(" + (i+1) + ")\n" +
                "Name: " + list.get(i).getUserName() + "\n" +
                "Email: " + list.get(i).getUserEmail() 
            );
        }
        System.out.println("\n");
        System.out.println("=============================");
    }

    private String GetSubjectContext(Scanner scanner)
    {
        System.out.print("Subject: ");
        String subject = scanner.nextLine();

        return subject;
    }

    private String GetMessageContext(Scanner scanner) {
        System.out.print("Message: ");
        String msg = scanner.nextLine();

        return msg;
    }
    
}
