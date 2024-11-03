//package p1;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class TaxiServiceRegister extends JFrame {
//
//    String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
//    String dbUsername = "root";
//    String dbPassword = "";
//
//    private JTextField nameField, emailField, cityField;
//    private JPasswordField passwordField, confirmPasswordField;
//    private JButton clearButton, backButton, registerButton;
//
//    public TaxiServiceRegister() {
//        // Set up the frame
//        setTitle("Taxi Service Register");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLayout(new BorderLayout());
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBackground(new Color(255, 215, 51));
//
//        JLabel logoLabel = new JLabel(new ImageIcon("C:\\Users\\Guest PC\\Desktop\\EAD project/logo.png"));
//        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainPanel.add(logoLabel);
//
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
//        formPanel.setBackground(new Color(255, 215, 51));
//        Font labelFont = new Font("Arial", Font.BOLD, 22);
//        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);
//
//        formPanel.add(createLabel("NAME:", labelFont));
//        nameField = createTextField(textFieldFont);
//        formPanel.add(nameField);
//
//        formPanel.add(createLabel("E-MAIL:", labelFont));
//        emailField = createTextField(textFieldFont);
//        formPanel.add(emailField);
//
//        formPanel.add(createLabel("CITY:", labelFont));
//        cityField = createTextField(textFieldFont);
//        formPanel.add(cityField);
//
//        formPanel.add(createLabel("Password:", labelFont));
//        passwordField = createPasswordField(textFieldFont);
//        formPanel.add(passwordField);
//
//        formPanel.add(createLabel("Confirm Password:", labelFont));
//        confirmPasswordField = createPasswordField(textFieldFont);
//        formPanel.add(confirmPasswordField);
//
//        mainPanel.add(formPanel);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(new Color(255, 215, 51));
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//
//        Font buttonFont = new Font("Arial", Font.BOLD, 20);
//        Dimension buttonSize = new Dimension(150, 50);
//
//        clearButton = createButton("Clear", buttonFont, buttonSize);
//        backButton = createButton("Back", buttonFont, buttonSize);
//        registerButton = createButton("Register", buttonFont, buttonSize);
//
//        clearButton.addActionListener(e -> clearForm());
//
//        backButton.addActionListener(e -> {
//            JOptionPane.showMessageDialog(null, "Are you sure!");
//            new LoginUI();
//            dispose();
//        });
//
//        registerButton.addActionListener(e -> register());
//
//        buttonPanel.add(clearButton);
//        buttonPanel.add(backButton);
//        buttonPanel.add(registerButton);
//
//        add(mainPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        pack();
//        setVisible(true);
//       
//    }
//
//    private JLabel createLabel(String text, Font font) {
//        JLabel label = new JLabel(text);
//        label.setFont(font);
//        label.setAlignmentX(Component.LEFT_ALIGNMENT);
//        return label;
//    }
//
//    private JTextField createTextField(Font font) {
//        JTextField textField = new JTextField(15);
//        textField.setFont(font);
//        textField.setMaximumSize(new Dimension(400, textField.getPreferredSize().height));
//        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
//        return textField;
//    }
//
//    private JPasswordField createPasswordField(Font font) {
//        JPasswordField passwordField = new JPasswordField(15);
//        passwordField.setFont(font);
//        passwordField.setMaximumSize(new Dimension(400, passwordField.getPreferredSize().height));
//        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
//        return passwordField;
//    }
//
//    private JButton createButton(String text, Font font, Dimension size) {
//        JButton button = new JButton(text);
//        button.setFont(font);
//        button.setPreferredSize(size);
//        return button;
//    }
//
//    private void clearForm() {
//        nameField.setText("");
//        emailField.setText("");
//        cityField.setText("");
//        passwordField.setText("");
//        confirmPasswordField.setText("");
//    }
//
//    private void register() {
//        String name = nameField.getText();
//        String email = emailField.getText();
//        String city = cityField.getText();
//        String password = new String(passwordField.getPassword());
//        String confirmPassword = new String(confirmPasswordField.getPassword());
//
//        if (name.isEmpty() || email.isEmpty() || city.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if (!isValidEmail(email)) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if (!password.equals(confirmPassword)) {
//            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//          
//        }
//
//        try {
//            // Load MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
//                String sql = "INSERT INTO customer (name, email, city, password) VALUES (?, ?, ?, ?)";
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.setString(1, name);
//                statement.setString(2, email);
//                statement.setString(3, city);
//                statement.setString(4, password);
//                statement.executeUpdate();
//
//                JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                BookingUI nextInterface = new BookingUI(getOpacity(), getOpacity());
//                clearForm();
//              
//            }
//        } catch (ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(this, "MySQL JDBC driver not found. Please add the driver library.", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Database error. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private boolean isValidEmail(String email) {
//        return email.contains("@") && email.endsWith(".com");
//    }
//}
