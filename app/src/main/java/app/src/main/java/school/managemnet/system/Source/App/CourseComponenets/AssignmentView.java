package app.src.main.java.school.managemnet.system.Source.App.CourseComponenets;

import javax.swing.*;

import app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes.AssignmentType;
import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;
import app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.User;

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
    public void launchAssignmentView(QueryAPI query, User user, Scanner sc) throws SQLException
    {  
        switch(user.getUserType())
        {
            case "teacher":
                final AssignmentType a_Teacher = query.OpenAssignment(user, sc);
                
                if(a_Teacher == null)
                {
                    System.out.println("No Assignments Currently");
                    return;
                }

                SwingUtilities.invokeLater(()->
                {
                    ShowAssignment(a_Teacher.GetImage());
                });
                break;

            case "student":
                final AssignmentType a_Student = query.OpenAssignment(user, sc);
            
                if(a_Student == null) {
                    System.out.println("No Assignments Currently");
                    return;
                }

                System.out.print("Do You Want To Take This Assignment (Yes/No): ");
                //Add Validation Here
                String input = sc.nextLine().toLowerCase();

                SwingUtilities.invokeLater(()->
                {
                    ShowAssignment(a_Student.GetImage());
                });

                if(input.equals("yes")) 
                {
                    try {
                        user.SubmitAssignment(query, sc, a_Student);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }   
                break;
        }
        
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
