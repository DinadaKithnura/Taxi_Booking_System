package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdminUI {

    // Constructor for Login UI
    public LoginAdminUI() {
    	
        String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
        String dbUsername = "root";
        String dbPassword = "";
        
        // Create a new frame for the Login page
        JFrame frame = new JFrame("Taxi Service - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(255, 215, 51));

        // Add Taxi Service image
        ImageIcon icon = new ImageIcon("C:\\Users\\Guest PC\\Desktop\\EAD project/logo.png"); // Update path accordingly
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(50, 100, 200, 200);
        frame.add(imageLabel);

        // Add Login label
        JLabel loginLabel = new JLabel("LOGIN ADMIN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 40));
        loginLabel.setBounds(400, 50, 200, 50);
        frame.add(loginLabel);

        // Add Username label and text field
        JLabel usernameLabel = new JLabel("USERNAME : ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setBounds(300, 150, 150, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(450, 150, 250, 30);
        frame.add(usernameField);

        // Add Password label and text field
        JLabel passwordLabel = new JLabel("PASSWORD : ");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(300, 200, 150, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(450, 200, 250, 30);
        frame.add(passwordField);

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 300, 100, 40);
        backButton.setBackground(Color.CYAN);
        frame.add(backButton);

        // Add Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(500, 300, 100, 40);
        loginButton.setBackground(Color.YELLOW);
        frame.add(loginButton);

        // Add ActionListener for the Login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Simple validation for username and password fields
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in both Username and Password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Database login validation
                    try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                        String sql = "SELECT * FROM admin WHERE name = ? AND password = ?";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, username);
                        statement.setString(2, password);
                        
                        ResultSet resultSet = statement.executeQuery();
                        
                        if (resultSet.next()) {
                            //JOptionPane.showMessageDialog(frame, "Login Successful!", "Info", JOptionPane.INFORMATION_MESSAGE);
                            AdminUI nextInterface = new AdminUI(); // Navigate to BookingUI
                            frame.dispose();
                            // Proceed to the next page or main admin panel
                        } else {
                            JOptionPane.showMessageDialog(frame, "Incorrect Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ActionListener for Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaxiAppUI nextPage = new TaxiAppUI();
                frame.dispose(); // Close the login window
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}
