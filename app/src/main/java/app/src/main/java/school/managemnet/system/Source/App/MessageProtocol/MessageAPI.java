package app.src.main.java.school.managemnet.system.Source.App.MessageProtocol;

import java.util.List;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.MessageType;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

public class MessageAPI {
    static public void SendMessage(MessageType msg, User user)
    {
        QueryAPI.QueryMessageApi(msg.GetSender_ID(), 
                    msg.GetRecipient_ID(), 
                    msg.GetSender(),
                    msg.GetSubject(), 
                    msg.GetMessage());
    }

    static public void ViewMessages(List<MessageType> msgs)
    {
        System.out.println("\n\n\n");
        for (MessageType msg : msgs)
        {
            System.out.println(
                "Sender: " + msg.GetSender() + "\n" +
                "==============================\n" +
                "Subject:\n" +
                "| " + msg.GetSubject() + " |\n" +
                "==============================\n" +
                "Message:\n" +
                msg.GetMessage() + "\n\n"
            );
        }
    }

}
