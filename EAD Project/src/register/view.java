package register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class view extends JFrame {
    private JTextField nameField, emailField, cityField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton clearButton, backButton, registerButton;

    public view() {
        setTitle("Taxi Service Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 215, 51));

        JLabel logoLabel = new JLabel(new ImageIcon("C:\\Users\\Guest PC\\Desktop\\EAD project/logo.png"));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255, 215, 51));
        Font labelFont = new Font("Arial", Font.BOLD, 22);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        formPanel.add(createLabel("NAME:", labelFont));
        nameField = createTextField(textFieldFont);
        formPanel.add(nameField);

        formPanel.add(createLabel("E-MAIL:", labelFont));
        emailField = createTextField(textFieldFont);
        formPanel.add(emailField);

        formPanel.add(createLabel("CITY:", labelFont));
        cityField = createTextField(textFieldFont);
        formPanel.add(cityField);

        formPanel.add(createLabel("Password:", labelFont));
        passwordField = createPasswordField(textFieldFont);
        formPanel.add(passwordField);

        formPanel.add(createLabel("Confirm Password:", labelFont));
        confirmPasswordField = createPasswordField(textFieldFont);
        formPanel.add(confirmPasswordField);

        mainPanel.add(formPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 215, 51));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Dimension buttonSize = new Dimension(150, 50);

        clearButton = createButton("Clear", buttonFont, buttonSize);
        backButton = createButton("Back", buttonFont, buttonSize);
        registerButton = createButton("Register", buttonFont, buttonSize);

        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(15);
        textField.setFont(font);
        textField.setMaximumSize(new Dimension(400, textField.getPreferredSize().height));
        return textField;
    }

    private JPasswordField createPasswordField(Font font) {
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(font);
        passwordField.setMaximumSize(new Dimension(400, passwordField.getPreferredSize().height));
        return passwordField;
    }

    private JButton createButton(String text, Font font, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(size);
        return button;
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getCityField() {
        return cityField.getText();
    }

    public String getPasswordField() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPasswordField() {
        return new String(confirmPasswordField.getPassword());
    }

    public void clearForm() {
        nameField.setText("");
        emailField.setText("");
        cityField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
