package app.src.main.java.school.managemnet.system.Source.App.UserOptions;


import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.DataTypes;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;
public class Contact implements OptionsInterface{

    @Override
    public void ExecuteOption(User user, DataTypes blob) {
        String subject = GetSubjectContext(blob.getScanner());
        String msg = GetMessageContext(blob.getScanner());
        user.Contact(subject, msg, blob);
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
