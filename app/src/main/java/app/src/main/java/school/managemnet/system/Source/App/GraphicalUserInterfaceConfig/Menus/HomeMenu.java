package app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HomeMenu implements Menus {

    @Override
    public JMenu Menu() {
        JMenu homeMenu = new JMenu("Home");

        JMenuItem profileItem = new JMenuItem("profile");
        homeMenu.add(profileItem);

        return homeMenu;
    }
    
}
