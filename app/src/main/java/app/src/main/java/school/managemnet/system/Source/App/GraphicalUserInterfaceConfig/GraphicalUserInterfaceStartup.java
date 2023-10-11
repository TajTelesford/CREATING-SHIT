package app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig;

import javax.swing.*;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Menus.HomeMenu;
import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Pages.HomePage;
import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Pages.SignIn;
import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.UtilityGraphicalInterface.UtilityVariable;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalUserInterfaceStartup extends JFrame{

    static public ConfigUserFromDatabaseResult User = null;
    static public QueryAPI queryAPI = null;
    static public JFrame frame;
    static public JPanel container = new JPanel();
    static private CardLayout cardLayout;
    static private JMenuBar mainMenuBar = new JMenuBar();
    
    public static void Run(QueryAPI api) {
        queryAPI = api;
        frame = new JFrame("School Management System");
        frame.setSize(UtilityVariable.Gui_Width, UtilityVariable.Gui_Height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a card layout for the container
        cardLayout = new CardLayout(5,6);
        container.setLayout(cardLayout);

        container.add(
            SignInPanel(), 
            "SignIn"
        );

        if(User != null)
        {
            SwitchPage("SignIn");
        }

        // Add components to the container
        frame.setJMenuBar(mainMenuBar);

        // Add the container to the content pane of the frame
        frame.getContentPane().add(container);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    static public void SwitchPage(String page)
    {
        cardLayout.show(container, page);
    }

    //Function Panels For Application
    static public JPanel HomePagePanel()
    {
        JPanel HomePageWrapper = new JPanel(new FlowLayout());
        HomePageWrapper.add(new HomePage().Page());

        return HomePageWrapper;
    }

    static private JPanel SignInPanel()
    {
        JPanel SignInWrapper = new JPanel(new FlowLayout()); 
        SignInWrapper.add(new SignIn().Page());

        return SignInWrapper;
    }

}
