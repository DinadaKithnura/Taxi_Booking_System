package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxiAppUI {
    
    // Constructor to set up the UI
    public TaxiAppUI() {
        // Create a new frame
        JFrame frame = new JFrame("CITY Taxi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen size
        frame.setLayout(null); // Absolute layout

        // Set background color
        frame.getContentPane().setBackground(new Color(255, 204, 51));

        // Load and display the image (use an actual file path in place of "taxiImage.png")
        ImageIcon icon = new ImageIcon("C:\\Users\\Guest PC\\Desktop\\EAD project/pickme.png");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(400, 140, 600, 300); // Adjust based on image size and screen

        // Add the "CITY Taxi" label
        JLabel titleLabel = new JLabel("CITY Taxi.");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 80));
        titleLabel.setBounds(550, 30, 400, 100); // Adjust for proper placement
        titleLabel.setForeground(Color.WHITE);

        // Add "Login Admin" button
        JButton loginButton = new JButton("Login Admin.");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
        loginButton.setBounds(400, 450, 250, 80); // Adjust the position and size
        loginButton.setBackground(Color.YELLOW);
        loginButton.setForeground(Color.BLACK);

        // Add "BOOK TAXI" button
        JButton bookButton = new JButton("BOOK TAXI.");
        bookButton.setFont(new Font("Arial", Font.PLAIN, 30));
        bookButton.setBounds(700, 450, 250, 80); // Adjust the position and size
        bookButton.setBackground(Color.YELLOW);
        bookButton.setForeground(Color.BLACK);

        // Action listener for the "Login Admin" button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for login action
                //JOptionPane.showMessageDialog(frame, "Login Admin button clicked!");
            	LoginAdminUI nextpage=new LoginAdminUI();
            	 frame.setVisible(false);
            }
        });

        // Action listener for the "BOOK TAXI" button
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for book taxi action
               // JOptionPane.showMessageDialog(frame, "Book Taxi button clicked!");
            	LoginUI nextpage=new LoginUI();
            	 frame.setVisible(false);
            
            }
        });

        // Add components to frame
        frame.add(titleLabel);
        frame.add(imageLabel);
        frame.add(loginButton);
        frame.add(bookButton);

        // Set frame visibility
       frame.setVisible(true);
    }
}
