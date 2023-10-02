package app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes;

public class AssignmentType {
    static byte[] image = null;
    static int Assignment_ID = -10000; //Random Number

    public AssignmentType(byte[] i, int id)
    {
        image = i;
        Assignment_ID = id;
    }

    static void SetImage(byte[] assignImage) { image = assignImage; }

    static void SetAssignmentID(int id) { Assignment_ID = id; }

    public byte[] GetImage() { return image; }

    public int GetAssignmentTypeID() { return Assignment_ID; }
    
}
