package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingUI {

    // Constructor for Booking UI
    public BookingUI(double tukTukCharge, double carCharge) {

        String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
        String dbUsername = "root";
        String dbPassword = "";

        // Create the frame
        JFrame frame = new JFrame("Taxi Service - Book Your Taxi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen size
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(255, 215, 51));

        // Add Taxi Service image logo
        ImageIcon icon = new ImageIcon("C:\\Users\\Guest PC\\Desktop\\EAD project/logo.png"); // Update the path as needed
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(50, 50, 200, 200);
        frame.add(imageLabel);

        // Title label
        JLabel titleLabel = new JLabel("BOOK YOUR TAXI....");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(400, 50, 400, 50);
        frame.add(titleLabel);

        // TUK TUK for 1KM label and text field (read-only)
        JLabel tukTukLabel = new JLabel("TUK TUK for 1KM : ");
        tukTukLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tukTukLabel.setBounds(400, 120, 200, 30);
        frame.add(tukTukLabel);

        JTextField tukTukField = new JTextField("100"); // Set default Tuk Tuk charge to 100
        tukTukField.setBounds(600, 120, 100, 30);
        tukTukField.setEditable(false);
        frame.add(tukTukField);

        // CAR for 1KM label and text field (read-only)
        JLabel carLabel = new JLabel("CAR for 1KM : ");
        carLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        carLabel.setBounds(400, 160, 200, 30);
        frame.add(carLabel);

        JTextField carField = new JTextField("200"); // Set default Car charge to 200
        carField.setBounds(600, 160, 100, 30);
        carField.setEditable(false);
        frame.add(carField);

        // Choose Taxi (Radio buttons)
        JLabel chooseTaxiLabel = new JLabel("Choose TAXI: ");
        chooseTaxiLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        chooseTaxiLabel.setBounds(400, 210, 150, 30);
        frame.add(chooseTaxiLabel);

        JRadioButton carRadioButton = new JRadioButton("CAR");
        carRadioButton.setBounds(550, 210, 100, 30);
        JRadioButton tukTukRadioButton = new JRadioButton("TUK TUK");
        tukTukRadioButton.setBounds(650, 210, 100, 30);

        // Group the radio buttons
        ButtonGroup taxiGroup = new ButtonGroup();
        taxiGroup.add(carRadioButton);
        taxiGroup.add(tukTukRadioButton);

        frame.add(carRadioButton);
        frame.add(tukTukRadioButton);

        // Date/Time Label and text field
        JLabel dateTimeLabel = new JLabel("Date/Time : ");
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dateTimeLabel.setBounds(400, 260, 150, 30);
        frame.add(dateTimeLabel);

        // Date picker initialized with the current date
        JTextField dateTimeField = new JTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        dateTimeField.setBounds(550, 260, 250, 30);
        frame.add(dateTimeField);

        // Contact NO label and text field
        JLabel emailLabel = new JLabel("Email : ");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setBounds(400, 310, 150, 30);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(550, 310, 250, 30);
        frame.add(emailField);

        // Clear button
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(400, 400, 150, 50);
        clearButton.setBackground(Color.CYAN);
        frame.add(clearButton);

        // Book button
        JButton bookButton = new JButton("BOOK");
        bookButton.setBounds(600, 400, 150, 50);
        bookButton.setBackground(Color.CYAN);
        frame.add(bookButton);

        // Exit button
        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(800, 400, 150, 50);
        exitButton.setBackground(Color.RED);
        frame.add(exitButton);

        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTimeField.setText("");
                emailField.setText("");
                taxiGroup.clearSelection();
            }
        });

        // Book button action
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTaxi = carRadioButton.isSelected() ? "CAR" : tukTukRadioButton.isSelected() ? "TUK TUK" : "";
                String dateTime = dateTimeField.getText();
                String email = emailField.getText();

                if (selectedTaxi.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select a taxi type.");
                    return;
                }

                try {
                    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                    String query = "INSERT INTO booking (vehical, dateTime, email) VALUES (?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, selectedTaxi);
                    statement.setString(2, dateTime);
                    statement.setString(3, email);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Booking successful!");

                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Booking failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Exit button action
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
