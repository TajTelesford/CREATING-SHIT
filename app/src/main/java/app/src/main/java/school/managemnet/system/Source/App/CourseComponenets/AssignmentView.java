package app.src.main.java.school.managemnet.system.Source.App.CourseComponenets;

import javax.swing.*;

import app.src.main.java.school.managemnet.system.Source.App.Database.Query;
import app.src.main.java.school.managemnet.system.Source.App.HeadlessConfig.DataConfigTypes.AssignmentType;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty.FacultyImpl;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Student.StudentImpl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class AssignmentView  
{
    //Async Function
    public AssignmentType launchAssignmentView(Query query, FacultyImpl teacher, StudentImpl student, Scanner sc) throws SQLException
    {  
        if(student == null)
        {
            final AssignmentType assignment = query.OpenAssignment(teacher, null, sc);
            SwingUtilities.invokeLater(()->
            {
                ShowAssignment(assignment.GetImage());
            });
        }
        if(teacher == null) 
        {
            final AssignmentType assignment = query.OpenAssignment(null, student, sc);
            if(student != null) // IT WILL NEVER BE NULL HERE LMAO JAVA TRIPPIN
            {
                System.out.print("Do You Want To Take This Assignment (Yes/No): ");
                //Add Validation Here
                String input = sc.nextLine().toLowerCase();

                SwingUtilities.invokeLater(()->
                {
                    ShowAssignment(assignment.GetImage());
                });

                if(input.equals("yes")) 
                {
                    try {
                        student.SubmitAssignment(query, sc, assignment);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }   

            }
            return assignment;
        }
        return null;
    }

    private void ShowAssignment(byte[] image)
    {
        SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if(image != null)
            {
                try {
                    // Convert the byte array to an Image
                    BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(image));

                // Scale the image to the specified dimensions
                    Image scaledImage = originalImage.getScaledInstance(800, 800, Image.SCALE_SMOOTH);

                // Create a BufferedImage from the scaled Image
                    BufferedImage bufferedImage = new BufferedImage(800, 1000, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = bufferedImage.createGraphics();
                    g2d.drawImage(scaledImage, 0, 0, null);
                    g2d.dispose();

                // Create an ImageIcon from the scaled image
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);

                // Create a JFrame to display the image
                    JFrame frame = new JFrame("Assignment");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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
                
            };
                return null;
            }
        };

        worker.execute();

    }
}
