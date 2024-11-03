package p1;

import javax.swing.*;
import register.view;
import register.controller;
import register.model;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginUI {

    String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
    String dbUsername = "root";
    String dbPassword = "";

    public LoginUI() {
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
        JLabel loginLabel = new JLabel("LOGIN");
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

        // Add "Don't have an Account? REGISTER" label
        JLabel registerLabel = new JLabel("Don't have an Account?");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        registerLabel.setBounds(300, 250, 200, 30);
        frame.add(registerLabel);

        JLabel registerLink = new JLabel("REGISTER");
        registerLink.setFont(new Font("Arial", Font.BOLD, 14));
        registerLink.setForeground(Color.RED);
        registerLink.setBounds(470, 250, 100, 30);
        frame.add(registerLink);

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
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                // Simple validation for username and password
                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in both Username and Password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Database validation
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                        String selectQuery = "SELECT * FROM customer WHERE name = ? AND password = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                        preparedStatement.setString(1, enteredUsername);
                        preparedStatement.setString(2, enteredPassword);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        // Check if resultSet contains any records
                        if (resultSet.next()) {
                           // JOptionPane.showMessageDialog(frame, "Login Successful!", "Message", JOptionPane.INFORMATION_MESSAGE);
                            BookingUI nextInterface = new BookingUI(0, 0); // Navigate to BookingUI
                            frame.dispose(); // Close the login frame
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Close resources
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Database Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ActionListener for Register link (placeholder, can redirect to registration page)
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(frame, "Go to Registration Page!", "Message", JOptionPane.INFORMATION_MESSAGE);
                view view = new view();
        		model model = new model();
        		controller con= new controller(model,view);
        		view.setVisible(true);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerLink.setForeground(Color.BLUE); // Optional hover effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerLink.setForeground(Color.RED); // Reset color when mouse exits
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
