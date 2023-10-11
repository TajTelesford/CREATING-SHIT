package app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Pages;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.GraphicalUserInterfaceStartup;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;

public class HomePage implements Pages {

    private JFrame frame = GraphicalUserInterfaceStartup.frame;
    private JPanel container;
    private BoxLayout boxLayout;

    public JPanel Page()
    {
        container = new JPanel();

        JLabel label = new JLabel("Welcome " + 
            ConfigureUserType(GraphicalUserInterfaceStartup.User));
        container.add(label);
        return container;
    }

    private String ConfigureUserType(ConfigUserFromDatabaseResult user)
    {
        if(user.user_type.equals("student"))
        {
            return user.getStudent().getUserName();
        }
        if(user.user_type.equals("teacher"))
        {
            return user.getFaculty().getUserName();
        }
        return null;
    }
}
