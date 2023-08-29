package source;
import java.util.Random;
public class User 
{
    private String UserType;
    private int ID;
    private String Name;
    static int[] IDCache;
    static int MAX_NUMBER_OF_IDS = 1000000;

    public enum UserTypes {
        Teacher, Student
    }

    public User(String type, String name)
    {
        if(IDCache == null) IDCache = new int[MAX_NUMBER_OF_IDS];
        UserType = type;
        Name = name;
        CreateID();
    }

    static int RandomNumber()
    {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER_OF_IDS);
    }

    public void CreateID()
    {
        int checkID = RandomNumber();
        boolean c = IDCreationCheck(checkID);
        do {
            checkID = RandomNumber();
            c = IDCreationCheck(checkID);
        } while (!c);
    } 
    
    public boolean IDCreationCheck(int id)
    {
        if(IDCache.length < 0) IDCache[0] = id;

        for(int i = 0; i < IDCache.length; i++)
        {
            if(id == IDCache[i]) return false;
        }
        return true;
    }

    public String getUserType(){ return UserType; }
    public int getID(){ return ID; }
    public String getName(){ return Name; }
}
