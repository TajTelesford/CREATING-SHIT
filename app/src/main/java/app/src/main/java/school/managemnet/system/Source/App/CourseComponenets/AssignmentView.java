package app.src.main.java.school.managemnet.system.Source.App.CourseComponenets;

import javax.swing.*;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class AssignmentView  
{

    //TODO: FIX FUNCTION TO ADD SUPPORT FOR STUDENTS
    public void launchAssignmentView(Query query, FacultyImpl teacher, Scanner sc) throws SQLException
    {   
        byte[] assignment = query.OpenAssignment(teacher, sc);
        ShowAssignment(assignment);

    }

    public void ShowAssignment(byte[] image)
    {
        if(image == null)
        {
            System.out.println("No Image Here");
            return;
        }

        try {
            // Convert the byte array to an Image
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            ImageIcon imageIcon = new ImageIcon(bufferedImage);

            // Create a JFrame to display the image
            JFrame frame = new JFrame("Image Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JLabel to display the image
            JLabel label = new JLabel(imageIcon);
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Set the JFrame size based on the image dimensions
            frame.pack();

            // Center the JFrame on the screen
            frame.setLocationRelativeTo(null);

            // Display the JFrame
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
