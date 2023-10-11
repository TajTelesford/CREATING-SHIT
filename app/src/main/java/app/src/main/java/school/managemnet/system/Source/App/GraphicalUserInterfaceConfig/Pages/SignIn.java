package app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import app.src.main.java.school.managemnet.system.Source.App.GraphicalUserInterfaceConfig.GraphicalUserInterfaceStartup;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.ConfigUser.ConfigUserFromDatabaseResult;


public class SignIn implements Pages{

    @Override
    public JPanel Page() {
        JPanel SignInPanel = new JPanel();
        // Use a BorderLayout for SignInPanel
        SignInPanel.setLayout(new BorderLayout());

        // Create a JPanel for form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 3));

        JLabel UserIDJLabel = new JLabel("User ID: ");
        JTextField UserID = new JTextField(20);

        JLabel passowrdJLabel = new JLabel("Password: ");
        JPasswordField passwordTextField = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        signInButton.setSize(new Dimension(100, 30));

        formPanel.add(UserIDJLabel);
        formPanel.add(UserID);
        formPanel.add(passowrdJLabel);
        formPanel.add(passwordTextField);
        formPanel.add(signInButton);

        SignInPanel.add(formPanel, BorderLayout.CENTER);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int ID = Integer.parseInt(UserID.getText());
                String password = new String(passwordTextField.getPassword());

                try {
                    // Attempt to log in and retrieve user data
                    ConfigUserFromDatabaseResult userData = GraphicalUserInterfaceStartup.queryAPI.Login_LogIntoDatabase(password, ID);

                    if (userData != null) {
                        // Login successful, store user data and perform necessary actions
                        GraphicalUserInterfaceStartup.User = userData;
                        GraphicalUserInterfaceStartup.container.add(
                            GraphicalUserInterfaceStartup.HomePagePanel(),
                            "HomePage"
                        );
                        GraphicalUserInterfaceStartup.SwitchPage("HomePage");
                    } else {
                        // Login failed, display an error message
                        JOptionPane.showMessageDialog(GraphicalUserInterfaceStartup.frame, "Login failed. Please check your credentials.");
                    }
                } catch (SQLException | NumberFormatException e) {
                    // Handle exceptions appropriately
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(GraphicalUserInterfaceStartup.frame, "An error occurred while trying to log in.");
                }
            }
        });

        return SignInPanel;
    }
    
}
