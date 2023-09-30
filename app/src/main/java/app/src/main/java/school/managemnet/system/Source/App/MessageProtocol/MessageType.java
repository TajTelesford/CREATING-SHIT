package app.src.main.java.school.managemnet.system.Source.App.MessageProtocol;

public class MessageType {
    private String sender;
    private int sender_id;
    private int recipient_id;
    private String subject;
    private String msg;

    public MessageType(int s_id, int r_id, String _sender, String sub, String mg)
    {
        sender = _sender;
        sender_id = s_id;
        recipient_id = r_id;
        subject = sub;
        msg = mg;
    }

    public String GetSender() { return sender; }

    public int GetSender_ID() { return sender_id; }

    public int GetRecipient_ID() { return recipient_id; }

    public String GetSubject() { return subject; }
    
    public String GetMessage() { return msg; }


}
